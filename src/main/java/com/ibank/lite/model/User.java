package com.ibank.lite.model;

import com.ibank.lite.util.Location;
import com.ibank.lite.util.Roles;
import com.ibank.lite.util.SocialState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Calendar;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastname;

    @Email
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    private Calendar birthday;

    @Column(name = "social_state", nullable = false,
            columnDefinition = "ENUM('WORK', 'STUDENT', 'NONWORK', 'RETIREE', 'DISABLED')")
    @Enumerated(value = EnumType.STRING)
    private SocialState socialState;

    private String password;

    @Column(name = "location", nullable = false,
            columnDefinition = "ENUM('CHISINAU', 'BALTI', 'TIRASPOL', 'EDINET', 'COMRAT','SOROCA', 'REZINA', 'STEFANVODA', 'RABNITA', 'BENDERI', 'CHADIRLUNGA', 'IALOVENI')")
    @Enumerated(value = EnumType.STRING)
    private Location location;

    private boolean active;

    @Column(name = "role", nullable = false,
            columnDefinition = "ENUM('ADMIN', 'USER')")
    @Enumerated(value = EnumType.STRING)
    private Roles roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<CreditRequest> creditRequestSets;
}
