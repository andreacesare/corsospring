package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Consente CORS su tutti gli endpoint
                .allowedOrigins("http://localhost:4200")  // Consente le richieste solo da Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Specifica i metodi HTTP consentiti
                .allowCredentials(true)
                .maxAge(3600);  // Tempo di vita della configurazione CORS
    }
}
