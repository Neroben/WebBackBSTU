package back.security;


import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

public class CustomOAuth2Provider {

    public static ClientRegistration getVk() {
        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId("vk");
        builder.clientAuthenticationMethod(ClientAuthenticationMethod.POST);
        //"{baseUrl/{action}/oauth2/code/{registrationId}"
        builder.scope(System.getProperty("spring.security.oauth2.client.registration.vk.scope"));
        builder.authorizationUri("https://oauth.vk.com/authorize?v=5.95");
        builder.tokenUri("https://oauth.vk.com/access_token");
        builder.userInfoUri("https://api.vk.com/method/users.get?{user_id}&v=5.95&fields=photo_id,verified,sex,bdate,city,country,photo_max,home_town,has_photo&display=popup&lang=ru&access_token=xxxxx");
        builder.clientName("vkontakte");
        builder.redirectUri("{baseUrl}/oauth2/callback/{registrationId}");
        builder.clientId(System.getProperty("spring.security.oauth2.client.registration.vk.client-id"));
        builder.clientSecret(System.getProperty("spring.security.oauth2.client.registration.vk.client-secret"));
        builder.userNameAttributeName("user_id");
        return builder.build();
    }

}
