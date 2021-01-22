package com.hillel.artemjev.phonebook.service;


import com.hillel.artemjev.phonebook.contact.Contact;
import com.hillel.artemjev.phonebook.contact.ContactType;

import java.util.List;

public interface ContactsService {

    List<Contact> getAll();

    void remove(int index);

    void add(String name, ContactType type, String contact);

    List<Contact> searchByPhonePart(String phoneToSearch);

    List<Contact> searchByNameBeginning(String nameToSearch);
}
