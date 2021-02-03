package com.hillel.artemjev.phonebook.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hillel.artemjev.phonebook.domain.Contact;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactsResponse {
    private String status;
    private List<Contact> contacts;
    private String error;
}
