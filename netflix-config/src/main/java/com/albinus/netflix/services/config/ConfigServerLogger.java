package com.albinus.netflix.services.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ConfigServerLogger {

    @Value("${spring.cloud.config.server.native.search-locations}")
    private String searchLocations;

    @PostConstruct
    public void logSearchLocations() {
        log.info("Config Server search-locations: {}", searchLocations);
    }
}