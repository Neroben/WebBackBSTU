package back.social.vkontakte.connect;

import back.social.vkontakte.api.VKontakte;
import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;

@Slf4j
public class VKontakteAdapter implements ApiAdapter<VKontakte> {

    private final VkApiClient vkApiClient;

    public VKontakteAdapter() {
        this.vkApiClient = new VkApiClient(HttpTransportClient.getInstance());
    }

    public boolean test(VKontakte vKontakte) {
        try {
            return !vkApiClient.users().get(vKontakte.getUserActor()).fields(Fields.SCREEN_NAME, Fields.PHOTO_200).lang(Lang.EN).execute().isEmpty();
        } catch (ApiException | ClientException e) {
            return false;
        }
    }

    public void setConnectionValues(VKontakte vKontakte, ConnectionValues values) {
        try {
            UserXtrCounters user = vkApiClient.users().get(vKontakte.getUserActor())
                    .fields(Fields.PHOTO_200)
                    .lang(Lang.EN).execute().get(0);
            values.setProviderUserId(String.valueOf(user.getId()));
            values.setDisplayName(user.getFirstName() + " " + user.getLastName());
            values.setProfileUrl("https://vk.com/id" + user.getId());
            values.setImageUrl(user.getPhoto200().toString());
        } catch (ApiException | ClientException e){
            log.error("Error while getting current user info.", e);
        }
    }

    public UserProfile fetchUserProfile(VKontakte vKontakte) {
        try {
            UserXtrCounters user = vkApiClient.users().get(vKontakte.getUserActor())
                    .fields(Fields.SCREEN_NAME, Fields.PHOTO_200)
                    .lang(Lang.EN).execute().get(0);
            return new UserProfileBuilder()
                    .setId(String.valueOf(user.getId()))
                    .setUsername(user.getScreenName())
                    .setFirstName(user.getFirstName())
                    .setLastName(user.getLastName())
                    .setName(user.getFirstName() + " " + user.getLastName())
                    .build();
        } catch (ApiException | ClientException e) {
            log.error("Error while getting current user info.", e);
            return new UserProfileBuilder().build();
        }
    }

    public void updateStatus(VKontakte vKontakte, String message) {}
}
