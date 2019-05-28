package com.caoliyuan.travelGuide.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class MyConfigurer implements WebMvcConfigurer {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
						.allowCredentials(true) //设置为true
						.maxAge(3600);;
		}

}
