package com.hillel.artemjev.phonebook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.hillel.artemjev.phonebook.menu.Menu;
import com.hillel.artemjev.phonebook.menu.actions.*;
import com.hillel.artemjev.phonebook.service.AccessToken;
import com.hillel.artemjev.phonebook.service.contacts.ContactsApiService;
import com.hillel.artemjev.phonebook.service.contacts.ContactsService;
import com.hillel.artemjev.phonebook.service.user.UserApiService;
import com.hillel.artemjev.phonebook.service.user.UserService;

import java.net.http.HttpClient;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HttpClient httpClient = HttpClient.newBuilder().build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        AccessToken token = new AccessToken();
        UserService userService = new UserApiService(httpClient, objectMapper, token);
        ContactsService contactsService = new ContactsApiService(httpClient, objectMapper, token);

        Menu menu = new Menu(sc);
        menu.addAction(new LoginMenuAction(userService, sc));
        menu.addAction(new RegistrationMenuAction(userService, sc));
        menu.addAction(new ReadAllUsersMenuAction(userService, sc));
//        menu.addAction(new ReadAllUsersWithAuthMenuAction(userService, sc));
        menu.addAction(new ReadAllContactsMenuAction(contactsService));
        menu.addAction(new SearchByNameMenuAction(contactsService, sc));
        menu.addAction(new SearchByContactMenuAction(contactsService, sc));
//        menu.addAction(new SearchByNameBeginningMenuAction(contactsService, sc));
//        menu.addAction(new SearchByPhonePartMenuAction(contactsService, sc));
//        menu.addAction(new RemoveContactMenuAction(contactsService, sc));
        menu.addAction(new AddContactMenuAction(contactsService, sc));
        menu.addAction(new ExitMenuAction());
        menu.run();
    }
}
