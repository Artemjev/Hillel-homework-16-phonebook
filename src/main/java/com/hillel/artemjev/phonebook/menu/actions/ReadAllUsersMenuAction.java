package com.hillel.artemjev.phonebook.menu.actions;

import com.hillel.artemjev.phonebook.domain.User;
import com.hillel.artemjev.phonebook.menu.MenuAction;
import com.hillel.artemjev.phonebook.service.user.UserService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class ReadAllUsersMenuAction implements MenuAction {
    final private UserService userService;
    final private Scanner sc;

    @Override
    public void doAction() {
        System.out.println("\n*********************************");
        System.out.println("Список пользователей (доступный без автоизации):");
        List<User> users = userService.getAll();
        users.stream().forEach(user -> System.out.println(user));
    }

    @Override
    public String getName() {
        return "Показать всех пользователей (без автоизации)";
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
