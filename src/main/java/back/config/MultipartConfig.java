package back.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@Getter
@ConfigurationProperties(prefix = "spring.servlet.multipart")
public class MultipartConfig {
    private String location;
}
