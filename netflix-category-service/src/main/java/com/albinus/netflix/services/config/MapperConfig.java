package com.albinus.netflix.services.config;

import com.albinus.netflix.lib.mapper.CategoryMapper;
import com.albinus.netflix.lib.mapper.CategoryMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public CategoryMapper categoryMapper() {
        return new CategoryMapperImpl();
    }
}
