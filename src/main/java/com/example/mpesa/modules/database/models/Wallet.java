package com.example.mpesa.modules.database.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "account_balance", nullable = false)
    private BigDecimal accountBalance;

    @Column(name = "account_holder", nullable = false)
    private String accountHolder;

    public Wallet() {

    }

    public Wallet(String accountHolder, BigDecimal accountBalance){
        this.accountHolder = accountHolder;
        this.accountBalance = accountBalance;
    }

    // getters and setters
    public UUID getId() {
        return id;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }
}
