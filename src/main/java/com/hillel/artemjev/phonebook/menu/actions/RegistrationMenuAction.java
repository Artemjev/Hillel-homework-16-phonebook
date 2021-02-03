package com.hillel.artemjev.phonebook.menu.actions;

import com.hillel.artemjev.phonebook.dto.LoginResponse;
import com.hillel.artemjev.phonebook.dto.RegistrationResponse;
import com.hillel.artemjev.phonebook.menu.MenuAction;
import com.hillel.artemjev.phonebook.service.user.UserService;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class RegistrationMenuAction implements MenuAction {
    final private UserService userService;
    final private Scanner sc;

    @Override
    public void doAction() {
        System.out.print("введите логин: ");
        String login = sc.nextLine();

        System.out.print("введите пароль: ");
        String password = sc.nextLine();

        System.out.print("введите дату рождения: ");
        String dateBorn = sc.nextLine();

        RegistrationResponse registrationResponse = userService.registration(login, password, dateBorn);

        if (registrationResponse.isSuccess()) {
            LoginResponse loginResponse = userService.login(login, password);
            userService.refreshToken(loginResponse.getToken());
        } else {
            //тут нужно выбросить свой эксепшн!!!
            System.out.println(registrationResponse.getError());
        }
    }

    @Override
    public String getName() {
        return "Зарегистрироваться";
    }

    @Override
    public boolean isVisible() {
        return !userService.hasToken();
    }
}
