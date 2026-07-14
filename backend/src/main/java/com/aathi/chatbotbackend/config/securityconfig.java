package com.aathi.chatbotbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class securityconfig {

    @Bean
    public RestClient restClient(){
        return RestClient.create();
    }
}
