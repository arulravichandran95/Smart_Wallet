package com.example.walletBackend.dto;

import com.example.walletBackend.entity.Transaction;
import java.math.BigDecimal;

public class TransactionRequest {
    private BigDecimal amount;
    private Transaction.TransactionType type;
    private String category;
    private String description;

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public Transaction.TransactionType getType() { return type; }
    public void setType(Transaction.TransactionType type) { this.type = type; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
