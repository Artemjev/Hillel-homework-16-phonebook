package com.hillel.artemjev.phonebook.service.contacts;


import com.hillel.artemjev.phonebook.domain.Contact;
import com.hillel.artemjev.phonebook.domain.ContactType;

import java.util.List;

public interface ContactsService {

    List<Contact> getAll();

    void remove(int index);

    void add(String name, ContactType type, String contact);

    List<Contact> searchByPhonePart(String phoneToSearch);

    List<Contact> searchByNameBeginning(String nameToSearch);

    List<Contact> searchByName(String nameToSearch);

    List<Contact> searchByContact(String contactToSearch);

    default boolean hasToken() {
        return true;
    }
}
