package com.example.walletBackend.controller;

import com.example.walletBackend.dto.MessageResponse;
import com.example.walletBackend.entity.User;
import com.example.walletBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/balance")
    public ResponseEntity<?> getBalance() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).get();
        return ResponseEntity.ok(Map.of("balance", user.getBalance()));
    }

    @PostMapping("/add-funds")
    public ResponseEntity<?> addFunds(@RequestBody Map<String, BigDecimal> request) {
        BigDecimal amount = request.get("amount");
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid amount"));
        }

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).get();
        
        user.setBalance(user.getBalance().add(amount));
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Funds added successfully! New balance: " + user.getBalance()));
    }
}
