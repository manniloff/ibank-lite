package com.ibank.lite.model;

import com.ibank.lite.util.Location;
import com.ibank.lite.util.Roles;
import com.ibank.lite.util.SocialState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull
    @Size(min = 3, max = 15, message = "First name length need minimum 3 and maximum 15 charts")
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    @Size(min = 3, max = 15, message = "Last name length need minimum 3 and maximum 15 charts")
    private String lastName;

    @Email
    @NotNull
    private String email;

    @Column(name = "phone_number")
    @NotNull
    @Size(min = 9, max = 12, message = "Phone number length need minimum 9 and maximum 12 charts")
    private String phoneNumber;

    @NotNull
    private Calendar birthday;

    @Column(name = "social_state", nullable = false,
            columnDefinition = "ENUM('WORK', 'STUDENT', 'NONWORK', 'RETIREE', 'DISABLED')")
    @Enumerated(value = EnumType.STRING)
    private SocialState socialState;

    @NotNull
    @Size(min = 6, max = 64, message = "Password length need minimum 6 and maximum 64 charts")
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
