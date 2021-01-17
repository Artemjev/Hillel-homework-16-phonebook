package com.hillel.artemjev.phonebook.contacts;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contact {
    private String name;
    private String phone;
}
