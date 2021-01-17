package com.hillel.artemjev.phonebook.menu.actions;

import com.hillel.artemjev.phonebook.contacts.Contact;
import com.hillel.artemjev.phonebook.service.ContactsService;
import com.hillel.artemjev.phonebook.menu.MenuAction;

import java.util.List;

public class ReadAllContactsMenuAction implements MenuAction {

    private ContactsService contactsService;

    public ReadAllContactsMenuAction(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @Override
    public void doAction() {
        System.out.println("\n*********************************");
        System.out.println("Список контактов:");
        List<Contact> contacts = contactsService.getAll();

        for (int i = 0; i < contacts.size(); i++) {
            System.out.printf("%3d - %s, %s\n", i + 1, contacts.get(i).getName(), contacts.get(i).getPhone());
        }
        System.out.println("*********************************");
    }

    @Override
    public String getName() {
        return "Показать список";
    }

}
