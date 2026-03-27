package com.example.walletBackend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    private String category;

    private String description;

    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    public enum TransactionType {
        INCOME, EXPENSE
    }

    public Transaction() {}

    public Transaction(User user, BigDecimal amount, TransactionType type, String category, String description, LocalDateTime timestamp) {
        this.user = user;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.description = description;
        this.timestamp = (timestamp == null) ? LocalDateTime.now() : timestamp;
    }

    public static TransactionBuilder builder() {
        return new TransactionBuilder();
    }

    public static class TransactionBuilder {
        private User user;
        private BigDecimal amount;
        private TransactionType type;
        private String category;
        private String description;
        private LocalDateTime timestamp = LocalDateTime.now();

        public TransactionBuilder user(User user) { this.user = user; return this; }
        public TransactionBuilder amount(BigDecimal amount) { this.amount = amount; return this; }
        public TransactionBuilder type(TransactionType type) { this.type = type; return this; }
        public TransactionBuilder category(String category) { this.category = category; return this; }
        public TransactionBuilder description(String description) { this.description = description; return this; }
        public TransactionBuilder timestamp(LocalDateTime timestamp) { this.timestamp = timestamp; return this; }
        public Transaction build() { return new Transaction(user, amount, type, category, description, timestamp); }
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
