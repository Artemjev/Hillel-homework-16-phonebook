package com.hillel.artemjev.phonebook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.hillel.artemjev.phonebook.menu.Menu;
import com.hillel.artemjev.phonebook.menu.actions.*;
import com.hillel.artemjev.phonebook.services.AccessToken;
import com.hillel.artemjev.phonebook.services.contacts.ApiContactsService;
import com.hillel.artemjev.phonebook.services.contacts.ContactsService;
import com.hillel.artemjev.phonebook.services.user.ApiUserService;
import com.hillel.artemjev.phonebook.services.user.UserService;
import com.hillel.artemjev.phonebook.util.ContactDtoBuilder;
import com.hillel.artemjev.phonebook.util.UserDtoBuilder;

import java.net.http.HttpClient;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String baseUri = "https://mag-contacts-api.herokuapp.com";
        UserDtoBuilder userDtoBuilder = new UserDtoBuilder();
        AccessToken token = new AccessToken();
        ObjectMapper mapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        HttpClient httpClient = HttpClient.newBuilder().build();
        UserService userService = new ApiUserService(
                baseUri,
                userDtoBuilder,
                token,
                mapper,
                httpClient
        );
        ContactDtoBuilder contactDtoBuilder = new ContactDtoBuilder();
        ContactsService contactsService = new ApiContactsService(
                userService,
                baseUri,
                contactDtoBuilder,
                mapper,
                httpClient
        );
        Scanner sc = new Scanner(System.in);

        Menu menu = new Menu(sc);
        menu.addAction(new LoginMenuAction(userService, sc));
        menu.addAction(new RegistrationMenuAction(userService, sc));
        menu.addAction(new ReadAllUsersMenuAction(userService, sc));
        menu.addAction(new ReadAllContactsMenuAction(contactsService));
        menu.addAction(new SearchByNameMenuAction(contactsService, sc));
        menu.addAction(new SearchByContactMenuAction(contactsService, sc));
        menu.addAction(new AddContactMenuAction(contactsService, sc));
        menu.addAction(new ExitMenuAction());
        menu.run();
    }
}
