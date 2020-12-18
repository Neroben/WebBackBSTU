package back.security;


import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

public class CustomOAuth2Provider {

    private CustomOAuth2Provider(){
    }

    public static ClientRegistration getVk() {
        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId("vk");
        builder.clientAuthenticationMethod(ClientAuthenticationMethod.POST);
        builder.scope("email");//доступ
        builder.authorizationUri("https://oauth.vk.com/authorize?v=5.95");
        builder.tokenUri("https://oauth.vk.com/access_token");
        builder.userInfoUri("https://api.vk.com/method/users.get?{user_id}&v=5.95&fields=photo_id,verified,sex,bdate,city,country,photo_max,home_town,has_photo&display=popup&lang=ru&access_token={access_token}");
        builder.clientName("vkontakte");
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);//-------
        builder.redirectUri("{baseUrl}/oauth2/callback/{registrationId}");//адрес куда возвращается код пользователя
        builder.clientId("7004922");
        builder.clientSecret("4DQyjH3uP8VphRMfGOyg");
        builder.userNameAttributeName("user_id");
        return builder.build();
    }

}
