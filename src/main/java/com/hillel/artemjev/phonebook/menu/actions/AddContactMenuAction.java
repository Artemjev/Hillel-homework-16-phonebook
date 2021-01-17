package com.hillel.artemjev.phonebook.menu.actions;

import com.hillel.artemjev.phonebook.service.ContactsService;
import com.hillel.artemjev.phonebook.menu.MenuAction;

import java.util.Scanner;


public class AddContactMenuAction implements MenuAction {

    private ContactsService contactsService;
    private Scanner sc;

    public AddContactMenuAction(ContactsService contactsService, Scanner sc) {
        this.contactsService = contactsService;
        this.sc = sc;
    }

    @Override
    public void doAction() {
        System.out.println("\n*********************************");
        System.out.println("Добавление контакта");
        System.out.print("Введите имя: ");
        String name = sc.nextLine();

        System.out.print("Введите телефон: ");
        String phone = sc.nextLine();

        if (!validatePhone(phone)) {
            System.out.println("Некорректный формат ввода.");
            System.out.println("Наиболее полный формат номер выгдядит так: +300000000000 , где 0 - любая цифра.");
            System.out.println("Введенный номер должен хотя-бы частично соответствовать этому формату.");
            System.out.println("Контакт не добавлен.");
            System.out.println("*********************************");
            return;
        }

        contactsService.add(name, phone);
        System.out.println("Контакт добавлен");
        System.out.println("*********************************");
    }

    @Override
    public String getName() {
        return "Добавить контакт";
    }

    //------------------------------------------------------------------------------
    private boolean validatePhone(String phone) {
        return phone.matches("(\\+?3)?\\d{0,11}");
    }
}


