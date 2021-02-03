package com.hillel.artemjev.phonebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactAddRequest {
    private String name;
    private String type;
    private String value;
}
