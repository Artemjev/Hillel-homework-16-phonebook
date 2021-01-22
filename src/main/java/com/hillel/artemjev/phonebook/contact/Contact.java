package com.hillel.artemjev.phonebook.contact;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contact {
    private String name;
    private ContactType type;
    private String contact;
}
