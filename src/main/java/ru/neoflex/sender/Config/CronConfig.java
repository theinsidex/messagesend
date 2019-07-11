package ru.neoflex.sender.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class CronConfig {

    @Value("${app.cron}")
    private String cronValue;

    @Bean
    public String cronBean() {
        return this.cronValue;
    }


}
