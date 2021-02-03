package com.hillel.artemjev.phonebook.menu.actions;

import com.hillel.artemjev.phonebook.domain.User;
import com.hillel.artemjev.phonebook.menu.MenuAction;
import com.hillel.artemjev.phonebook.service.user.UserService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class ReadAllUsersWithAuthMenuAction implements MenuAction {
    final private UserService userService;
    final private Scanner sc;

    @Override
    public String getName() {
        return "Показать всех пользователей (с авторизацией)";
    }

    @Override
    public void doAction() {
        System.out.println("\n*********************************");
        System.out.println("Список пользователей (доступный авторизацией):");
        List<User> users = userService.getAllWithAuth();
        users.stream().forEach(user -> System.out.println(user));

    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
