package com.thirdparty.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.thirdparty.exception.RestTemplateExceptionHandler;

/**
 * @author Nikhil.Tirmanwar
 *
 */
@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder, RestTemplateExceptionHandler restTemplateExceptionHandler) {
        return restTemplateBuilder.errorHandler(restTemplateExceptionHandler).build();
    }
}
