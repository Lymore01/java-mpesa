package com.example.mpesa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class HttpClientConfig {
    @Bean
    public RestClient mpesaRestClient() {
        return RestClient.builder()
            .baseUrl("https://sandbox.safaricom.co.ke")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }
}
