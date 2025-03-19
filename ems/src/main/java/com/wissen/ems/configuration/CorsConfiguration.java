package com.wissen.ems.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wissen.ems.common.Constants;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class CorsConfiguration {
	private EmployeeManagementSystemConfigurationProperties configurationProperties;

	// WebMvcConfigurer allows us to customize the default Spring MVC configuration.
	// We need to modify the CORS (Cross-Origin Resource Sharing) settings to allow
	// the front-end to be able to access the back-end. Without this modification,
	// the browser will block the front-end from making API requests to the back-end
	// due to the Same-Origin Policy.
	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping(Constants.REST_API_BASE_URI_PATH + "/**")
					.allowedOrigins(configurationProperties.getFrontendUrl())
					.allowedMethods("POST", "GET")
					.allowedHeaders("*");
			}
		};
	}
}
