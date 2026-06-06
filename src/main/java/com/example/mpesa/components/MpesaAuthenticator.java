package com.example.mpesa.components;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


@Component
public class MpesaAuthenticator { 

    private final RestClient mpesaRestClient;

    public MpesaAuthenticator(RestClient mpesaRestClient) {
        this.mpesaRestClient = mpesaRestClient;
    }

    private record MpesaAuthResponse(
    String access_token,
    String expires_in
) {}

    public String generateToken(String consumerKey, String consumerSecret) {
        String credentials = consumerKey + ":" + consumerSecret;
        String encodedCredentials = Base64.getEncoder()
                .encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        
        String authUrl = "/oauth/v1/generate?grant_type=client_credentials";

        MpesaAuthResponse response = mpesaRestClient.get()
                .uri(authUrl)
                .header("Authorization", "Basic " + encodedCredentials)
                .retrieve()
                .body(MpesaAuthResponse.class);
        return response.access_token();
    }
}