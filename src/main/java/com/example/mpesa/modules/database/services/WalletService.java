package com.example.mpesa.modules.database.services;

import com.example.mpesa.modules.database.models.Wallet;
import com.example.mpesa.modules.database.repositories.WalletRepository;
import java.math.BigDecimal;
import java.util.UUID;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    private final WalletRepository walletRepository;   

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Wallet createNewWallet(String customerName){
        Wallet newWallet = new Wallet(customerName, BigDecimal.ZERO);
        return walletRepository.save(newWallet);
    }

    @Transactional
    public void depositFunds(UUID walletId, BigDecimal amount){
        Wallet wallet = walletRepository.findById(walletId).orElseThrow(
            () -> new IllegalArgumentException("Wallet not found")
        );

        BigDecimal newBalance = wallet.getAccountBalance().add(amount);
        wallet.setBalance(newBalance);
    }

    @Transactional
public Wallet getWalletByAccountHolder(String accountHolder) {
    return walletRepository.findByAccountHolder(accountHolder)
            .orElseThrow(() -> new IllegalArgumentException("Wallet not found for customer: " + accountHolder));
}
}
