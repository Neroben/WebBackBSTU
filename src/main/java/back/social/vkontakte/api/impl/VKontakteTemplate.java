package back.social.vkontakte.api.impl;

import back.social.vkontakte.api.VKontakte;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

public class VKontakteTemplate extends AbstractOAuth2ApiBinding implements VKontakte {

    private UserActor userActor;
    private ServiceActor serviceActor;

    private final Integer providerUserId;
    private final String email;
    private final String accessToken;
    private final Integer clientId;
    private final String clientSecret;

    public VKontakteTemplate() {
        this.providerUserId = null;
        this.accessToken = null;
        this.clientId = null;
        this.clientSecret = null;
        this.email = null;
        initialize();
    }

    public VKontakteTemplate(Integer providerUserId, String email, String accessToken, Integer clientId, String clientSecret) {
        this.providerUserId = providerUserId;
        this.accessToken = accessToken;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.email = email;
        initialize();
    }

    private void initialize() {
        userActor = new UserActor(providerUserId, accessToken);
        serviceActor = new ServiceActor(clientId, clientSecret, accessToken);
    }


    public ServiceActor getServiceActor() {
        return serviceActor;
    }

    public UserActor getUserActor() {
        return userActor;
    }

    public String getEmail() {
        return email;
    }
}
