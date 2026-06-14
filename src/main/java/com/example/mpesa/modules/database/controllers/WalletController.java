package com.example.mpesa.modules.database.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


import com.example.mpesa.modules.database.dto.CreateWalletRequest;
import com.example.mpesa.modules.database.models.Wallet;
import com.example.mpesa.modules.database.services.WalletService;
import com.example.mpesa.modules.database.dto.DepositRequest;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<Wallet> createNewWallet(@RequestBody CreateWalletRequest requestPayload){
        Wallet newWallet = walletService.createNewWallet(requestPayload.customerName());
        
        return ResponseEntity.ok(newWallet);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Void> depositFunds(@RequestBody DepositRequest requestPayload){
        walletService.depositFunds(requestPayload.walletId(), requestPayload.amount());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{accountHolder}")
    public ResponseEntity<Wallet> getWalletByAccountHolder(@PathVariable String accountHolder){
        Wallet wallet = walletService.getWalletByAccountHolder(accountHolder);
        return ResponseEntity.ok(wallet);
    }

}