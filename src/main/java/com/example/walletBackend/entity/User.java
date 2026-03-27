package com.example.walletBackend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    
    @Column(unique = true, nullable = true)
    private String email;

    private BigDecimal balance = BigDecimal.ZERO;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    public User() {}

    public User(String username, String email, String password, BigDecimal balance, Set<String> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.roles = roles;
    }

    // Static Builder-like method for convenience
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private String username;
        private String email;
        private String password;
        private BigDecimal balance = BigDecimal.ZERO;
        private Set<String> roles;

        public UserBuilder username(String username) { this.username = username; return this; }
        public UserBuilder email(String email) { this.email = email; return this; }
        public UserBuilder password(String password) { this.password = password; return this; }
        public UserBuilder balance(BigDecimal balance) { this.balance = balance; return this; }
        public UserBuilder roles(Set<String> roles) { this.roles = roles; return this; }
        public User build() { return new User(username, email, password, balance, roles); }
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }
}
