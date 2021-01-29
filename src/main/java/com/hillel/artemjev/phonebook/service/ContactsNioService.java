package com.hillel.artemjev.phonebook.service;

import com.hillel.artemjev.phonebook.contact.Contact;
import com.hillel.artemjev.phonebook.util.ContactParser;
import com.hillel.artemjev.phonebook.contact.ContactType;
import com.hillel.artemjev.phonebook.util.NioFileUtil;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ContactsNioService implements ContactsService {
    private Path path;
    private ContactParser parser;
    private NioFileUtil fileUtil;

    public ContactsNioService(Path path, ContactParser parser, NioFileUtil fileUtil) {
        this.path = path;
        this.parser = parser;
        this.fileUtil = fileUtil;
        fileUtil.createFileIfNotExist();
    }

    public ContactsNioService(String pathString, ContactParser parser, NioFileUtil fileUtil) {
        this(Path.of(pathString), parser, fileUtil);
    }

    @Override
    public List<Contact> getAll() {
        List<Contact> contactList = new LinkedList<>();
        fileUtil.readByLine(str -> contactList.add(parser.parse(str)));
        return contactList.stream().filter(contact -> contact != null).collect(Collectors.toList());
    }

    public void remove(int index) {
        List<Contact> contactList = getAll();
        contactList.remove(index);
        fileUtil.cleanFile();
        for (Contact contact : contactList) {
            fileUtil.writeString(parser.toString(contact) + "\n");
        }

    }

    @Override
    public void add(String name, ContactType type, String contact) {
        String contactStr = parser.toString(new Contact(name, type, contact)) + "\n";
        if (contactStr != null) {
            fileUtil.writeString(contactStr);
        }
    }

    @Override
    public List<Contact> searchByPhonePart(String phoneToSearch) {
        List<Contact> contactList = new LinkedList<>();
        fileUtil.readByLine(str -> {
            Contact contact = parser.parse(str);
            if (contact != null
                    && contact.getType().equals(ContactType.PHONE)
                    && contact.getContact().contains(phoneToSearch)) {
                contactList.add(contact);
            }
        });
        return contactList;
    }

    @Override
    public List<Contact> searchByNameBeginning(String nameToSearch) {
        List<Contact> contactList = new LinkedList<>();
        fileUtil.readByLine(str -> {
            Contact contact = parser.parse(str);
            if (contact != null && contact.getName().toUpperCase().startsWith(nameToSearch.toUpperCase())) {
                contactList.add(contact);
            }
        });
        return contactList;
    }
}
