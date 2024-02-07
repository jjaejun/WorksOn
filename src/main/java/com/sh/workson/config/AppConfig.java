package com.sh.workson.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.LinkedHashSet;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    ModelMapper modelMapperSet() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(setToLongConverter());
        return modelMapper;
    }
    @Bean
    public Converter<LinkedHashSet<Long>, Long> setToLongConverter() {
        return context -> {
            LinkedHashSet<Long> source = context.getSource();
            if (source != null && !source.isEmpty()) {
                return source.iterator().next();
            }
            return null;
        };
    }
}
