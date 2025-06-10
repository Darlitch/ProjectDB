package com.db.project.frontend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Статические ресурсы
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/frontend/static/");
        
        // Vendor ресурсы
        registry.addResourceHandler("/vendor/**")
                .addResourceLocations("classpath:/frontend/static/vendor/");
        
        // CSS ресурсы
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/frontend/static/css/");
        
        // JS ресурсы
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/frontend/static/js/");
        
        // Webjars
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("/webjars/")
                .resourceChain(false);
    }
} 