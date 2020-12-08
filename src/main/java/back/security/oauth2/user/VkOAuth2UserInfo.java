package back.security.oauth2.user;

import java.util.Map;

//todo поправить
public class VkOAuth2UserInfo extends OAuth2UserInfo{

    public VkOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return null;
    }
}
