
package com.example.mpesa.services;

import com.example.mpesa.components.MpesaAuthenticator;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import com.example.mpesa.dto.Payload;
import org.springframework.web.client.RestClient;
import com.example.mpesa.dto.MpesaStkPushResponse;

@Service
public class PaymentProcessor { 

    private final MpesaAuthenticator mpesaAuthenticator; 
    private final RestClient mpesaRestClient;
    private final String consumerKey;
    private final String consumerSecret;
    private final String shortCode;
    private final String passKey;
    private final String callback_url;

    private String generateTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return ZonedDateTime.now(ZoneOffset.UTC).format(formatter);
    }

    private String generatePassword(){
        String timestamp = generateTimestamp();
        String password = Base64.getEncoder().encodeToString((shortCode + passKey + timestamp).getBytes(StandardCharsets.UTF_8));
        return password;
    }
    
    public PaymentProcessor(
        MpesaAuthenticator mpesaAuthenticator,
        RestClient mpesaRestClient,
        @Value("${MPESA_CONSUMER_KEY}") String consumerKey,
        @Value("${MPESA_CONSUMER_SECRET}") String consumerSecret,
        @Value("${MPESA_SHORT_CODE}") String shortCode,
        @Value("${MPESA_PASS_KEY}") String passKey,
        @Value("${CALLBACK_URL:https://mydomain.com}") String callbackUrl
    ) {
        this.mpesaAuthenticator = mpesaAuthenticator;
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.shortCode = shortCode;
        this.passKey = passKey;
        this.mpesaRestClient = mpesaRestClient;
        this.callback_url = callbackUrl;
    }


   
    public MpesaStkPushResponse processPayment() {
        String accessToken = mpesaAuthenticator.generateToken(consumerKey, consumerSecret);
        String password = generatePassword();
        String timestamp = generateTimestamp();
        Payload payload = new Payload(
            shortCode,
            password,
            timestamp,
            "CustomerPayBillOnline",
            "29700",
            "254745478832",
            shortCode,
            "254745478832",
            callback_url + "/api/payment/stk-push-callback",
            "Usitense Ednah ni sisi",
            "Testing stk push"
        );

        try {
            MpesaStkPushResponse response = mpesaRestClient.post()
                .uri("/mpesa/stkpush/v1/processrequest")
                .header("Authorization", "Bearer " + accessToken)
                .body(payload)
                .retrieve()
                .body(MpesaStkPushResponse.class);
            
            return response;
        } catch (Exception e) {
            System.err.println("Error during STK Push: " + e.getMessage());
        }   
        return null;
    }
}

