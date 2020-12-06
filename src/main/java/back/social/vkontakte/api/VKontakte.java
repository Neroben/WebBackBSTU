package back.social.vkontakte.api;

import org.springframework.social.ApiBinding;

import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.client.actors.UserActor;

public interface VKontakte extends ApiBinding {

    ServiceActor getServiceActor();

    UserActor getUserActor();

    String getEmail();
}
