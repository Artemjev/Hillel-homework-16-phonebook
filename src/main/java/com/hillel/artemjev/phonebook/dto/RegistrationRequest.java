package com.hillel.artemjev.phonebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String login;
    private String password;
    private String dateBorn;
}
