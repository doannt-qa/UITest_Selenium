package configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource("classpath:configuration.properties")
public class ProfileConfig {

    @Value("${cloudfrontUrl}")
    private String cloudfrontUrl;

    @Value("${browser}")
    private String browser;

    @PostConstruct
    public void init() {
        System.out.println("Cloudfront: " + cloudfrontUrl);
        System.out.println("browser: " + browser);
        Hooks.cloudfrontUrl = cloudfrontUrl;
        Hooks.browser = browser;
    }
}