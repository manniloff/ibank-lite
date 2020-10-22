package com.ibank.lite.model;

import com.ibank.lite.util.CreditType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "credit_type", nullable = false,
            columnDefinition = "ENUM('PERSONAL_LOAN_WITHOUT_COLLATERAL', 'MORTGAGE', 'PERSONAL_LOAN', 'PRIMA_CASA', 'AUTO_CREDIT', 'OVERDRAFT')")
    @Enumerated(value = EnumType.STRING)
    private CreditType creditType;

    @Column(name = "loan_term")
    private int loanTerm;

    private double rate;
}
