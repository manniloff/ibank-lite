package com.ibank.lite.repository;

import com.ibank.lite.model.CreditRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreditRequestRepository extends JpaRepository<CreditRequest, Long> {
    @Query("select r from CreditRequest r where r.user.id = :userId")
    List<CreditRequest> findAllByUserId(@Param("userId") long userId);
}
