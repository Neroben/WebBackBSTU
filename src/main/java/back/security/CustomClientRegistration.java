package back.security;

import org.springframework.security.oauth2.client.registration.ClientRegistration;

public class CustomClientRegistration {

    public ClientRegistration getRegistration(String registrationId) {

        ClientRegistration clientRegistration = null;

        if (registrationId.equals("vk")) {
            clientRegistration = CustomOAuth2Provider.getVk();
        }

        return clientRegistration;
    }

}
