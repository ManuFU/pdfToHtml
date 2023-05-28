package com.example.pdftohtmlKotlin.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry
            .addResourceHandler("/html/**")
            .addResourceLocations("classpath:/public/")
            .setCachePeriod(60 * 20)  // cache for 20 minutes
    }
}

