package com.ibank.lite.service.impl;

import com.ibank.lite.model.Bank;
import com.ibank.lite.repository.BankRepository;
import com.ibank.lite.service.BankService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankServiceIml implements BankService {
    private static final Logger LOGGER = LoggerFactory.getLogger("repo");
    private final BankRepository bankRepository;

    @Override
    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    @Override
    public Optional<Bank> findById(int id) {
        return bankRepository.findById(id);
    }

    @Override
    public long create(Bank newBank) {
        LOGGER.info("Creating Bank with name {}", newBank.getName());

        Bank bank = new Bank();

        bank.setName(newBank.getName());
        bank.setPhoneNumber(newBank.getPhoneNumber());
        bank.setPassword(newBank.getPassword());
        bank.setWebSite(newBank.getWebSite());
        bank.setEmail(newBank.getEmail());
        bank.setAddress(newBank.getAddress());

        return bankRepository.save(bank).getId();
    }

    @Override
    public Optional<Bank> update(Bank newBank, int id) {
        if (bankRepository.findById(id).isPresent()) {
            return bankRepository.findByName(newBank.getEmail())
                                 .map(bank -> {
                                     LOGGER.info("Updating Bank with Id - {} and name - {}", bank.getId(),
                                             bank.getName());

                                     bank.setAddress(newBank.getAddress());
                                     bank.setPhoneNumber(newBank.getPhoneNumber());
                                     bank.setPassword(newBank.getPassword());
                                     bank.setWebSite(newBank.getWebSite());
                                     bank.setEmail(newBank.getEmail());
                                     bankRepository.save(bank);
                                     return newBank;
                                 });
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(int id) {
        Optional<Bank> bank = bankRepository.findById(id);
        if (bank.isPresent()) {
            LOGGER.info("Deleting Bank by Id - {} from db", id);
            bankRepository.deleteById(id);
        }
    }
}
