package com.hillel.artemjev.phonebook.entities;

import lombok.Data;

@Data
public class Contact {
    private Integer id;
    private String name;
    private ContactType type;
    private String contact;

    public enum ContactType {PHONE, EMAIL,}
}
