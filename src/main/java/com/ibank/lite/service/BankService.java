package com.ibank.lite.service;

import com.ibank.lite.model.Bank;

import java.util.List;
import java.util.Optional;

public interface BankService {
    List<Bank> findAll();

    Optional<Bank> findById(int id);

    long create(Bank newBank);

    Optional<Bank> update(Bank newBank, int id);

    void deleteById(int id);
}
