package back.social.vkontakte.connect;

import back.social.vkontakte.api.VKontakte;
import back.social.vkontakte.api.impl.VKontakteTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VKontakteServiceProvider extends AbstractOAuth2ServiceProvider<VKontakte> {

    private final String clientSecret;
    private final Integer clientId;

    public VKontakteServiceProvider(String clientId, String clientSecret) {
        super(new VKontakteOAuth2Template(clientId, clientSecret));
        this.clientSecret = clientSecret;
        this.clientId = Integer.parseInt(clientId);
    }

    public VKontakte getApi(String accessToken) {
        Integer providerUserId = null;
        Matcher idMatcher = Pattern.compile("\\[id=([^]]*)]").matcher(accessToken);
        if(idMatcher.find()) {
            providerUserId = Integer.valueOf(idMatcher.group(1));
            accessToken = accessToken.replaceAll("\\[id=" + providerUserId + "]", "");
        }

        String email = null;
        Matcher emailMatcher = Pattern.compile("\\[email=([^]]*)]").matcher(accessToken);
        if(emailMatcher.find()) {
            email = emailMatcher.group(1);
            accessToken = accessToken.replaceAll("\\[email=" + email + "]", "");
        }

        return new VKontakteTemplate(providerUserId, email, accessToken, clientId, clientSecret);
    }
}
