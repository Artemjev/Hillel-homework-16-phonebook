package com.hillel.artemjev.phonebook.menu.actions;

import com.hillel.artemjev.phonebook.domain.ContactType;
import com.hillel.artemjev.phonebook.service.contacts.ContactsService;
import com.hillel.artemjev.phonebook.menu.MenuAction;

import java.util.Scanner;


public class AddContactMenuAction implements MenuAction {
    final private ContactsService contactsService;
    final private Scanner sc;

    public AddContactMenuAction(ContactsService contactsService, Scanner sc) {
        this.contactsService = contactsService;
        this.sc = sc;
    }

    @Override
    public void doAction() {
        if (!contactsService.hasToken()) {
            System.out.println("Время сеанса истекло. Неообходимо повторно войти в систему.\n");
            return;
        }
        System.out.println("\n*********************************");
        System.out.println("Добавление контакта");
        System.out.print("Введите имя: ");
        String name = sc.nextLine();

        System.out.print("Введите тип контакта (PHONE/EMAIL): ");
        ContactType type;
        try {
            type = ContactType.valueOf(sc.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректно введен тип контакта.");
            System.out.println("Контакт не добавлен.");
            System.out.println("*********************************");
            return;
        }

        System.out.printf("Введите %s: ", type);
        String contact = sc.nextLine();
        if (!validateContact(contact, type)) {
            System.out.printf("Некорректный формат ввода %s.\n", type);
            System.out.println("Контакт не добавлен.");
            System.out.println("*********************************");
            return;
        }

        contactsService.add(name, type, contact);
        System.out.println("Контакт добавлен");
        System.out.println("*********************************");
    }

    @Override
    public String getName() {
        return "Добавить контакт";
    }

    @Override
    public boolean isVisible() {
        return contactsService.hasToken();
    }

    //------------------------------------------------------------------------------
    private boolean validateContact(String contact, ContactType type) {
        boolean isValid = false;
        switch (type) {
            case PHONE:
                isValid = contact.matches("(?:\\+380|380|80|0)?(\\d{1,9})");
                break;
            case EMAIL:
                isValid = contact.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+");
                ;
                break;
        }
        return isValid;
    }
}


