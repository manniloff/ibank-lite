package com.ibank.lite.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotNull
    @Size(min = 4, max = 15, message = "Bank name length need minimum 3 and maximum 15 charts")
    private String name;

    @Column(name = "phone_number", nullable = false)
    @NotNull
    @Size(min = 9, max = 12, message = "Phone number length need minimum 9 and maximum 12 charts")
    private String phoneNumber;

    @Column(nullable = false)
    @NotNull
    @Size(min = 6, max = 64, message = "Password length need minimum 9 and maximum 12 charts")
    private String password;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @NotNull
    @Size(min = 6, max = 64, message = "Address length need minimum 9 and maximum 12 charts")
    private String address;

    @Column(name = "web_site")
    private String webSite;
}
