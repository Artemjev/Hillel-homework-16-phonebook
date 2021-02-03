package com.hillel.artemjev.phonebook.service.contacts;

import com.hillel.artemjev.phonebook.domain.Contact;
import com.hillel.artemjev.phonebook.util.ContactParser;
import com.hillel.artemjev.phonebook.domain.ContactType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;

public class ContactsFileService implements ContactsService {
    private File file;
    private ContactParser parser;

    public ContactsFileService(File file, ContactParser parser) {
        this.file = file;
        this.parser = parser;
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ContactsFileService(String filePathname, ContactParser parser) {
        this.file = new File(filePathname);
        this.parser = parser;
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Contact> getAll() {
        List<Contact> contactList = new LinkedList<>();
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

    //было несколько вариантов, как реализовать этот метод. Выбрал самый простой сугубо из-за нехватки времени.
    @Override
    public void remove(int index) {
        List<Contact> contactList = getAll();
        contactList.remove(index);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true), true)) {
            Files.newBufferedWriter(file.toPath(), StandardOpenOption.TRUNCATE_EXISTING);
            contactList.stream().forEach(contact -> writer.println(parser.toString(contact)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(String name, ContactType type, String contact) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true), true)) {
            writer.println(parser.toString(new Contact(name, type, contact)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Contact> searchByPhonePart(String phoneToSearch) {
        List<Contact> contactList = new LinkedList<>();
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

    //------------------------------------------------------------------
    @Override
    public List<Contact> searchByName(String nameToSearch) {
        return null;
    }

    @Override
    public List<Contact> searchByContact(String contactToSearch) {
        return null;
    }
}
