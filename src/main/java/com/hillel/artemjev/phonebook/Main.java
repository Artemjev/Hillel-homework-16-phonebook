package com.hillel.artemjev.phonebook;

import com.hillel.artemjev.phonebook.menu.actions.*;
import com.hillel.artemjev.phonebook.menu.Menu;
import com.hillel.artemjev.phonebook.service.ContactsService;
import com.hillel.artemjev.phonebook.service.InMemoryContactsService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ContactsService contactsService = new InMemoryContactsService();
        contactsService.add("Aaa", "111");
        contactsService.add("bbb", "222");
        contactsService.add("ccc", "333");
        contactsService.add("DDd", "444");
        contactsService.add("eee", "555");
        contactsService.add("fff", "777");
        contactsService.add("Ggg", "777");
        contactsService.add("aabc", "888");

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
