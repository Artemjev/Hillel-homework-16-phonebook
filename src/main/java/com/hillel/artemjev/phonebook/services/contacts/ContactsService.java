package com.hillel.artemjev.phonebook.services.contacts;


import com.hillel.artemjev.phonebook.entities.Contact;

import java.util.List;

public interface ContactsService {

    void remove(Integer id);

    void add(Contact contact);

    List<Contact> getAll();

    List<Contact> findByName(String name);

    List<Contact> findByValue(String value);

    default boolean isAuth() {
        return true;
    }
}
