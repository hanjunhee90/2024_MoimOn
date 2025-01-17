package com.future.my.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 로컬 경로 C:/dev_spring/uploads 를 /uploads/** URL로 매핑
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:///C:/dev_spring/uploads/");
    }

}
