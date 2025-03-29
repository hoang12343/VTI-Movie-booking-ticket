package vti_galaxy.configs.env;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class envConfig {
    private static final Dotenv dotenv = Dotenv
            .configure()
            .directory("../configs/env")
            .ignoreIfMissing()
            .load();

    @Bean
    public Dotenv env() {
        return dotenv;
    }
}
