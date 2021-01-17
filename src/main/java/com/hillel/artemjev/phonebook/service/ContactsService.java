package com.hillel.artemjev.phonebook.service;


import com.hillel.artemjev.phonebook.contacts.Contact;

import java.util.List;

public interface ContactsService {

    List<Contact> getAll();

    void remove(int index);

    void add(String name, String phone);

    List<Contact> searchByPhonePart(String phoneToSearch);

    List<Contact> searchByNameBeginning(String nameToSearch);
}
