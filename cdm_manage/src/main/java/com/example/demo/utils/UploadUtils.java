package com.example.demo.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class UploadUtils extends WebMvcConfigurationSupport {
    
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("file:" + System.getProperty("user.dir")+"\\src\\main\\resources\\static\\");
        super.addResourceHandlers(registry);
    }
}
