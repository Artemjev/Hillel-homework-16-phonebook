package com.hillel.artemjev.phonebook.menu.actions;

import com.hillel.artemjev.phonebook.domain.Contact;
import com.hillel.artemjev.phonebook.menu.MenuAction;
import com.hillel.artemjev.phonebook.service.contacts.ContactsService;

import java.util.List;
import java.util.Scanner;

public class SearchByPhonePartMenuAction implements MenuAction {
    private ContactsService contactsService;
    private Scanner sc;

    public SearchByPhonePartMenuAction(ContactsService contactsService, Scanner sc) {
        this.contactsService = contactsService;
        this.sc = sc;
    }

    @Override
    public String getName() {
        return "Поиск по части номера";
    }

    @Override
    public void doAction() {
        if (!contactsService.hasToken()) {
            System.out.println("Время сеанса истекло. Неообходимо повторно войти в систему.\n");
            return;
        }
        System.out.println("\n*********************************");
        System.out.println("Поиск по части номера");
        System.out.print("Введите часть номера: ");
        String phoneToSearch = sc.nextLine();

        if (!validatePhone(phoneToSearch)) {
            System.out.println("Некорректный формат ввода.");
            System.out.println("Наиболее полный формат номер выгдядит так: +380?????????");
            System.out.println(", где ? - любая цифра. Часть номера должна соответствовать этому формату.");
            System.out.println("*********************************");
            return;
        }

        List<Contact> foundContactsList = contactsService.searchByPhonePart(phoneToSearch);
        if (foundContactsList.size() != 0) {
            System.out.println("Найдены следующие контакты:");
            foundContactsList.stream().forEach(System.out::println);
        } else {
            System.out.println("Контакты не найдены.");
        }
        System.out.println("*********************************");
    }

    @Override
    public boolean isVisible() {
        return contactsService.hasToken();
    }

    //------------------------------------------------------------------------------
    private boolean validatePhone(String phone) {
        return phone.matches("(?:\\+380|380|80|0)?(\\d{1,9})");
    }
}
