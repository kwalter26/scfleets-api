package com.fusionkoding.citizenshqapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Configuration
public class RestTemplateConifg {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
