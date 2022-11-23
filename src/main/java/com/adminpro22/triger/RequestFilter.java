package com.adminpro22.triger;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Component
@EnableWebMvc
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestFilter implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("https://admin-pro21-fe.uc.r.appspot.com","https://admin-pro-80ad0.web.app","http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT","DELETE");
    }

}
