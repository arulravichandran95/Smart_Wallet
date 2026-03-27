package com.example.walletBackend.controller;

import com.example.walletBackend.dto.*;
import com.example.walletBackend.entity.*;
import com.example.walletBackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionRequest transactionRequest) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).get();

        BigDecimal amount = transactionRequest.getAmount();
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid amount"));
        }

        // Update balance
        if (transactionRequest.getType() == Transaction.TransactionType.EXPENSE) {
            if (user.getBalance().compareTo(amount) < 0) {
                return ResponseEntity.badRequest().body(new MessageResponse("Insufficient balance"));
            }
            user.setBalance(user.getBalance().subtract(amount));
        } else {
            user.setBalance(user.getBalance().add(amount));
        }

        userRepository.save(user);

        // Record transaction
        Transaction transaction = Transaction.builder()
                .user(user)
                .amount(amount)
                .type(transactionRequest.getType())
                .category(transactionRequest.getCategory())
                .description(transactionRequest.getDescription())
                .timestamp(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

        return ResponseEntity.ok(new MessageResponse("Transaction recorded successfully!"));
    }

    @GetMapping("/history")
    public ResponseEntity<List<Transaction>> getTransactionHistory() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).get();
        
        List<Transaction> history = transactionRepository.findByUserOrderByTimestampDesc(user);
        return ResponseEntity.ok(history);
    }
}
