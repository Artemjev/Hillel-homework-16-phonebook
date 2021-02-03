package com.hillel.artemjev.phonebook.menu.actions;

import com.hillel.artemjev.phonebook.dto.LoginResponse;
import com.hillel.artemjev.phonebook.menu.MenuAction;
import com.hillel.artemjev.phonebook.service.user.UserService;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class LoginMenuAction implements MenuAction {
    final private UserService userService;
    final private Scanner sc;

    @Override
    public String getName() {
        return "Войти";
    }

    @Override
    public void doAction() {
        System.out.print("введите логин: ");
        String login = sc.nextLine();

        System.out.print("введите пароль: ");
        String password = sc.nextLine();

        LoginResponse loginResponse = userService.login(login, password);

        if (loginResponse.isSuccess()) {
            userService.refreshToken(loginResponse.getToken());
        } else {
            //тут нужно выбросить свой эксепшн
            System.out.println(loginResponse.getError());
        }
    }

    @Override
    public boolean isVisible() {
        return !userService.hasToken();
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
