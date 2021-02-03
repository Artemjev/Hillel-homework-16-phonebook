package com.hillel.artemjev.phonebook.dto;

import lombok.Data;

@Data
public class RegistrationResponse {
    private String status;
    private String error;

    public boolean isSuccess() {
        return "ok".equalsIgnoreCase(status);
    }
}
