package com.ibank.lite.dto;

import com.ibank.lite.util.SocialState;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private int age;
    private int numberOfChildren;
    private SocialState socialState;
}
