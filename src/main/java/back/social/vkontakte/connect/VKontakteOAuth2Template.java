package back.social.vkontakte.connect;

import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;

import java.util.Map;

public class VKontakteOAuth2Template extends OAuth2Template {

    public VKontakteOAuth2Template(String clientId, String clientSecret) {
        super(clientId, clientSecret, "http://oauth.vk.com/authorize", "https://oauth.vk.com/access_token");
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected AccessGrant createAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, Map<String, Object> response) {
        if(response.get("user_id") != null)
            accessToken += "[id=" + response.get("user_id") + "]";
        if(response.get("email") != null)
            accessToken += "[email=" + response.get("email") + "]";
        return new VkAccessGrant(accessToken, scope, refreshToken, expiresIn == 0 ? null : expiresIn, (Integer) response.get("user_id"));
    }
}
