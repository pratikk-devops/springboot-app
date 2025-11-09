package com.pg.PGTools.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve images from the application's classpath `static/images` first
        // and fall back to an external folder (used in production) if present.
        registry.addResourceHandler("/images/**")
            .addResourceLocations("classpath:/static/images/", "file:/home/ubuntu/project/images/");
    }
}
