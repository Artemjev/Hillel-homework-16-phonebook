package com.hillel.artemjev.phonebook.menu.actions;

import com.hillel.artemjev.phonebook.contact.Contact;
import com.hillel.artemjev.phonebook.menu.MenuAction;
import com.hillel.artemjev.phonebook.service.ContactsService;

import java.util.List;
import java.util.Scanner;

public class SearchByNameBeginningMenuAction implements MenuAction {
    private ContactsService contactsService;
    private Scanner sc;

    public SearchByNameBeginningMenuAction(ContactsService contactsService, Scanner sc) {
        this.contactsService = contactsService;
        this.sc = sc;
    }

    @Override
    public String getName() {
        return "Поиск по началу имени";
    }

    @Override
    public void doAction() {
        System.out.println("\n*********************************");
        System.out.println("Поиск по началу имени");
        System.out.print("Введите имя: ");
        String nameToSearch = sc.nextLine();

        List<Contact> foundContactsList = contactsService.searchByNameBeginning(nameToSearch);

        if (foundContactsList.size() != 0) {
            System.out.println("Найдены следующие контакты:");
            foundContactsList.stream().forEach(System.out::println);
        } else {
            System.out.println("Контакты не найдены.");
        }
        System.out.println("*********************************");
    }
}
