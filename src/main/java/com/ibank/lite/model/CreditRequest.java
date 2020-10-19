package com.ibank.lite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibank.lite.util.MaritalStatus;
import com.ibank.lite.util.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "credit_sum")
    private int creditSum;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Email
    private String email;

    private int age;

    @Column(name = "request_status", nullable = false,
            columnDefinition = "ENUM('NEW', 'ACTIVE', 'IN_PROGRESS', 'CLOSED')")
    @Enumerated(value = EnumType.STRING)
    private RequestStatus requestStatus;

    @Column(name = "number_of_children")
    private int numberOfChildren;

    @Column(name = "marital_status", nullable = false,
            columnDefinition = "ENUM('MARRIED', 'DIVORCED', 'NOT_MARRIED', 'WIDOW')")
    @Enumerated(value = EnumType.STRING)
    private MaritalStatus maritalStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
}
