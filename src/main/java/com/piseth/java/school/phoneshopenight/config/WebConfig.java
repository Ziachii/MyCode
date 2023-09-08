package com.piseth.java.school.phoneshopenight.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //todo will allow all url
        //registry.addMapping("/**");
        //todo allow only one url we wanted
        registry.addMapping("/**").
                allowedOrigins("http://localhost:4200","http://localhost:4201");
    }
}
