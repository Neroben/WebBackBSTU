package back.security.oauth2;

import back.entity.User;
import back.exception.OAuth2AuthenticationProcessingException;
import back.repository.UserRepository;
import back.security.UserPrincipal;
import back.security.oauth2.user.OAuth2UserInfo;
import back.security.oauth2.user.OAuth2UserInfoFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.naming.AuthenticationException;
import javax.security.auth.message.AuthException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User;
        if(oAuth2UserRequest.getClientRegistration().getRegistrationId().equals("vk"))
            oAuth2User = loadVkUser(oAuth2UserRequest);
        else
            oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex){
            throw new AuthException(ex.getMessage());
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) throws AuthenticationException  {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        User socialUser;
        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        if(userOptional.isPresent()) {
            socialUser = userOptional.get();
            if (!socialUser.getProvider().equals(oAuth2UserRequest.getClientRegistration().getRegistrationId()))
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        socialUser.getProvider() + " account. Please use your " + socialUser.getProvider() +
                        " account to login.");
            socialUser = updateExistingUser(socialUser, oAuth2UserInfo);
        } else
            socialUser = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);

        return UserPrincipal.create(socialUser, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();

        user.setProvider(oAuth2UserRequest.getClientRegistration().getRegistrationId());
        user.setProviderId(oAuth2UserInfo.getId());
        user.setName(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(existingUser);
    }

    private OAuth2User loadVkUser(OAuth2UserRequest oAuth2UserRequest) {
        RestTemplate template = new RestTemplate();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", oAuth2UserRequest.getAccessToken().getTokenType().getValue() + " " + oAuth2UserRequest.getAccessToken().getTokenValue());
        HttpEntity<?> httpRequest = new HttpEntity<>(headers);
        String uri = oAuth2UserRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri();
        String userNameAttributeName = oAuth2UserRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        uri = uri.replace("{user_id}", userNameAttributeName + "=" + oAuth2UserRequest.getAdditionalParameters().get(userNameAttributeName));

        try {
            ResponseEntity<Object> entity = template.exchange(uri, HttpMethod.GET, httpRequest, Object.class);
            Map<String, Object> response = (Map<String, Object>) entity.getBody();
            ArrayList<?> valueList = (ArrayList<?>) response.get("response");
            Map<String, Object> userAttributes = (Map<String, Object>) valueList.get(0);
            userAttributes.put(userNameAttributeName, oAuth2UserRequest.getAdditionalParameters().get(userNameAttributeName));

            Set<GrantedAuthority> authorities = Collections.singleton(new OAuth2UserAuthority(userAttributes));
            return new DefaultOAuth2User(authorities, userAttributes, userNameAttributeName);

        } catch (HttpClientErrorException ex) {
            ex.printStackTrace();
            throw new OAuth2AuthenticationProcessingException(ex.getMessage());
        }
    }
}
