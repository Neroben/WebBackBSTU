package back.social.vkontakte.connect;

import back.social.vkontakte.api.VKontakte;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;

public class VKontakteConnectionFactory extends OAuth2ConnectionFactory<VKontakte> {

    public VKontakteConnectionFactory(String clientId, String clientSecret) {
        super("vkontakte", new VKontakteServiceProvider(clientId, clientSecret), new VKontakteAdapter());
    }

    @Override
    public boolean supportsStateParameter() {
        // https://github.com/vkolodrevskiy/spring-social-vkontakte/issues/14
        return false;
    }

    @Override
    public String extractProviderUserId(AccessGrant accessGrant) {
        return ((VkAccessGrant) accessGrant).getUserId().toString();
    }

}