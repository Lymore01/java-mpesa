package com.example.mpesa.dto;

public record MpesaStkPushResponse(
    String MerchantRequestID,
    String CheckoutRequestID,
    String ResponseCode,
    String ResponseDescription,
    String CustomerMessage
    ){}