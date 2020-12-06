package back.config;

import back.social.vkontakte.connect.VKontakteConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;

@Configuration
@EnableSocial
@PropertySource("classpath:social/social-cfg.properties")
public class SocialConfig implements SocialConfigurer {

    private boolean autoSignUp;

    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        this.autoSignUp = Boolean.parseBoolean(environment.getProperty("social.auto-signup"));
        connectionFactoryConfigurer.addConnectionFactory(
                new VKontakteConnectionFactory(
                    environment.getProperty("vk.client.id"),
                    environment.getProperty("vk.client.secret")
                )
        );
    }

    public UserIdSource getUserIdSource() {
        return null;
    }

    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return null;
    }
}
