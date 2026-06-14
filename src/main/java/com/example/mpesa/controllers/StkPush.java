package com.example.mpesa.controllers;

import com.example.mpesa.services.PaymentProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.mpesa.dto.MpesaStkPushResponse;


@RestController
public class StkPush {
    private final PaymentProcessor paymentProcessor;

    public StkPush(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    @GetMapping("/stk")
    public void stkPush() {
        MpesaStkPushResponse response = paymentProcessor.processPayment();
        System.out.println("Response: " + response);
    
    }
}
