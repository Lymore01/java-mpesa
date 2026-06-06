package com.example.mpesa.dto;

public record Payload(String BusinessShortCode, String Password, String Timestamp, String TransactionType,
        String Amount, String PartyA, String PartyB, String PhoneNumber, String CallBackURL, String AccountReference,
        String TransactionDesc) {
}
