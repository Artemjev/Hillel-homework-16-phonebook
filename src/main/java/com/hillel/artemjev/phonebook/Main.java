package com.hillel.artemjev.phonebook;

import com.hillel.artemjev.phonebook.contact.ContactParser;
import com.hillel.artemjev.phonebook.contact.ContactType;
import com.hillel.artemjev.phonebook.menu.actions.*;
import com.hillel.artemjev.phonebook.menu.Menu;
import com.hillel.artemjev.phonebook.service.ContactsService;
import com.hillel.artemjev.phonebook.service.FileContactsService;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File file = new File("contacts.txt");
        if (file.exists()) {
            file.delete();
        }

        ContactsService contactsService = new FileContactsService(file, new ContactParser());
        contactsService.add("Aaa", ContactType.valueOf("PHONE"), "111");
        contactsService.add("bbb", ContactType.valueOf("PHONE"), "222");
        contactsService.add("ccc", ContactType.valueOf("PHONE"), "333");
        contactsService.add("DDd", ContactType.valueOf("PHONE"), "444");
        contactsService.add("eee", ContactType.valueOf("PHONE"), "555");
        contactsService.add("fff", ContactType.valueOf("PHONE"), "777");
        contactsService.add("Ggg", ContactType.valueOf("PHONE"), "777");
        contactsService.add("aabc", ContactType.valueOf("PHONE"), "888");
        contactsService.add("Aaa", ContactType.valueOf("EMAIL"), "Aaa@gmail.com");
        contactsService.add("bbb", ContactType.valueOf("EMAIL"), "bbb@gmail.com");
        contactsService.add("ccc", ContactType.valueOf("EMAIL"), "ccc@gmail.com");
        contactsService.add("DDd", ContactType.valueOf("EMAIL"), "DDd@gmail.com");
        contactsService.add("eee", ContactType.valueOf("EMAIL"), "eee@gmail.com");
        contactsService.add("fff", ContactType.valueOf("EMAIL"), "fff@gmail.com");
        contactsService.add("Ggg", ContactType.valueOf("EMAIL"), "Ggg@gmail.com");
        contactsService.add("aabc", ContactType.valueOf("EMAIL"), "aabc@gmail.com");

        Menu menu = new Menu(sc);
        menu.addAction(new ReadAllContactsMenuAction(contactsService));
        menu.addAction(new AddContactMenuAction(contactsService, sc));
        menu.addAction(new RemoveContactMenuAction(contactsService, sc));
        menu.addAction(new SearchByPhonePartMenuAction(contactsService, sc));
        menu.addAction(new SearchByNameBeginningMenuAction(contactsService, sc));
        menu.addAction(new ExitMenuAction());
        menu.run();
    }
}
