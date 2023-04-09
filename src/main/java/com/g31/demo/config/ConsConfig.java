package com.g31.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Anthony HE, anthony.zj.he@outlook.com
 * @Date 9/4/2023
 * @Description:
 */
@Configuration
public class ConsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                // Exposing other attributes in the header to client applications.
                // If this attribute is not set, the frontend will not be able to
                // retrieve the Authorization (i.e., token) from the response header.
                .exposedHeaders("Authorization")
                .allowCredentials(false)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }
}

