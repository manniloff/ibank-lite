package com.ibank.lite.repository;

import com.ibank.lite.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Integer> {
    Optional<Bank> findByName(String bankName);
}
