package com.hillel.artemjev.phonebook.services.contacts;

import com.hillel.artemjev.phonebook.entities.Contact;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class InMemoryContactsService implements ContactsService {

    private final List<Contact> contacts;

    public InMemoryContactsService() {
        contacts = new ArrayList<>();
    }

    @Override
    public void add(Contact contact) {
        contact.setId(newId());
        contacts.add(contact);
    }

    @Override
    public void remove(Integer id) {
        contacts.stream().filter(c -> !c.getId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public List<Contact> getAll() {
        return contacts;
    }

    @Override
    public List<Contact> findByName(String name) {
        return contacts.stream()
                .filter(contact -> contact.getName().toUpperCase().startsWith(name.toUpperCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Contact> findByValue(String value) {
        return contacts.stream()
                .filter(contact -> contact.getContact().toUpperCase().contains(value.toUpperCase()))
                .collect(Collectors.toList());
    }

    //------------------------------------------------------------------
    private int newId() {
        return contacts.stream()
                .map(contact -> contact.getId())
                .max(Comparator.comparingInt(a -> a))
                .map(id -> id + 1)
                .orElse(1);
    }
}


