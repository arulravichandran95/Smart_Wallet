package com.example.walletBackend.repository;

import com.example.walletBackend.entity.Transaction;
import com.example.walletBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserOrderByTimestampDesc(User user);
}
