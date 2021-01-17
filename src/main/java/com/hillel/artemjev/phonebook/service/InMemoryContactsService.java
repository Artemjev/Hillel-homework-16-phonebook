package com.hillel.artemjev.phonebook.service;

import com.hillel.artemjev.phonebook.contacts.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class InMemoryContactsService implements ContactsService {

    private List<Contact> contacts;

    public InMemoryContactsService() {
        contacts = new ArrayList<>();
    }

    @Override
    public List<Contact> getAll() {
        return contacts;
    }

    @Override
    public void remove(int index) {
        contacts.remove(index);
    }

    @Override
    public void add(String name, String phone) {
        contacts.add(new Contact(name, phone));
    }

    @Override
    public List<Contact> searchByPhonePart(String phoneToSearch) {
        return contacts.stream()
                .filter(contact -> contact.getPhone().contains(phoneToSearch))
                .collect(Collectors.toList());
    }

    @Override
    public List<Contact> searchByNameBeginning(String nameToSearch) {
        return contacts.stream()
                .filter(contact -> contact.getName().toUpperCase().startsWith(nameToSearch.toUpperCase()))
                .collect(Collectors.toList());
    }
}
