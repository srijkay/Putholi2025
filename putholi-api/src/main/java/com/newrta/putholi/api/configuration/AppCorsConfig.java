package com.newrta.putholi.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Configuration
public class AppCorsConfig implements WebMvcConfigurer {
    
    /**
     * 
     */
    @Value("${api.cors.url}")
    private String corsUrl;
    
    /**
     *
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
	registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").maxAge(6000);
    }

}
