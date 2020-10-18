package com.ibank.lite.model;

import com.ibank.lite.util.CreditType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
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


    private CreditType creditType;

    //@Column(name="credit_range")
    //private CreditRange creditRange;

    @Column(name = "loan_term")
    private int loanTerm;

    //@Column(name= "issuance_commission")
    //private double issuanceCommission;

    private double rate;
}
