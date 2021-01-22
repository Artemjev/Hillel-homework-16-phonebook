package com.hillel.artemjev.phonebook.service;

import com.hillel.artemjev.phonebook.contact.Contact;
import com.hillel.artemjev.phonebook.contact.ContactParser;
import com.hillel.artemjev.phonebook.contact.ContactType;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileContactsService implements ContactsService {
    private File file;

    public FileContactsService(File file) {
        this.file = file;
    }

    public FileContactsService(String filePathname) {
        this.file = new File(filePathname);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Contact> getAll() {
        List<Contact> contactList = new LinkedList<>();
        ContactParser parser = new ContactParser();
        try (BufferedReader br = new BufferedReader(new FileReader(file));) {
            String str;
            while ((str = br.readLine()) != null) {
                contactList.add(parser.parse(str));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contactList;
    }

    @Override
    public void remove(int index) {

    }

    @Override
    public void add(String name, ContactType type, String contact) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true), true)) {
            writer.printf("%s[%s:%s]\n", name, type, contact);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Contact> searchByPhonePart(String phoneToSearch) {
        List<Contact> contactList = new LinkedList<>();
        ContactParser parser = new ContactParser();
        try (BufferedReader br = new BufferedReader(new FileReader(file));) {
            String str;
            while ((str = br.readLine()) != null) {
                Contact contact = parser.parse(str);
                if (contact.getType().equals(ContactType.PHONE) && contact.getContact().contains(phoneToSearch)) {
                    contactList.add(contact);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contactList;
    }

    @Override
    public List<Contact> searchByNameBeginning(String nameToSearch) {
        List<Contact> contactList = new LinkedList<>();
        ContactParser parser = new ContactParser();
        try (BufferedReader br = new BufferedReader(new FileReader(file));) {
            String str;
            while ((str = br.readLine()) != null) {
                Contact contact = parser.parse(str);
                if (contact.getName().toUpperCase().startsWith(nameToSearch.toUpperCase())) {
                    contactList.add(contact);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contactList;
    }
}
