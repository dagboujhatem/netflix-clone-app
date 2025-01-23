package com.albinus.netflix.services.config;

import com.albinus.netflix.lib.mapper.UserMapper;
import com.albinus.netflix.lib.mapper.UserMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public UserMapper categoryMapper() {
        return new UserMapperImpl();
    }
}
