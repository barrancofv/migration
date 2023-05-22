package es.codeurjc.books.configuration;

import es.codeurjc.books.service.user.UserService;
import es.codeurjc.books.service.user.UserServiceImpl;
import es.codeurjc.books.service.user.UserServiceMSImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.logging.Logger;

@Configuration
public class AppConfiguration {

    public static final Logger LOGGER = Logger.getLogger(AppConfiguration.class.getName());

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        return builder.setConnectTimeout(Duration.ofMillis(300000))
                .setReadTimeout(Duration.ofMillis(300000)).build();
    }

    @Bean (name = "architecture")
    @ConditionalOnProperty(prefix="app.architecture", name="model", havingValue = "USERS_IN_MONOLITH")
    public UserService architectureMonolith() {
        LOGGER.info("--------------------Monolith architecture--------------------");
        return new UserServiceImpl();
    }

    @Bean (name = "architecture")
    @ConditionalOnProperty(prefix="app.architecture", name="model", havingValue = "USERS_IN_MICROSERVICE")
    public UserService architectureMs() {
        LOGGER.info("--------------------MS architecture--------------------");
        return new UserServiceMSImpl();
    }
}
