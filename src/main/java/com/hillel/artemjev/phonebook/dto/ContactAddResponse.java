package com.hillel.artemjev.phonebook.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactAddResponse {
    private String status;
    private String error;

    public boolean isSuccess() {
        return "ok".equalsIgnoreCase(status);
    }
}

