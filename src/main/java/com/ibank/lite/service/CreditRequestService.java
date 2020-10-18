package com.ibank.lite.service;

import com.ibank.lite.model.CreditRequest;

import java.util.List;
import java.util.Optional;

public interface CreditRequestService {
    List<CreditRequest> findAll();

    Optional<CreditRequest> findById(long id);

    long create(CreditRequest newCreditRequest);

    Optional<CreditRequest> update(CreditRequest newCreditRequest, long id);

    void deleteById(long id);

    List<CreditRequest> findAllByUserId(long userId);
}
