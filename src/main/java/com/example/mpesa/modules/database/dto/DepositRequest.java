package com.example.mpesa.modules.database.dto;
import java.math.BigDecimal;
import java.util.UUID;

public record DepositRequest(
    UUID walletId, 
    BigDecimal amount
) {}