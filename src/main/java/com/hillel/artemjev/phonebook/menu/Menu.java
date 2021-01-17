package com.hillel.artemjev.phonebook.menu;

import java.util.Arrays;
import java.util.Scanner;


public class Menu {
    private MenuAction[] actions;
    private Scanner sc;

    public Menu(Scanner scanner) {
        this.actions = new MenuAction[0];
        this.sc = scanner;
    }

    public Menu(MenuAction[] actions, Scanner sc) {
        this.actions = actions;
        this.sc = sc;
    }

    public void addAction(MenuAction action) {
        actions = Arrays.copyOf(actions, actions.length + 1);
        actions[actions.length - 1] = action;
    }

    public void run() {
        while (true) {
            showMenu();
            int choice = getMenuIndexFromUser();

            if (!validateMenuIndex(choice)) {
                System.out.printf("Значение должно быть от 1 до %d.\n", actions.length);
                continue;
            }

            actions[choice].doAction();
            if (actions[choice].closeAfter()) break;
        }
    }

    //-----------------------------------------------------------------------------------------------
    private void showMenu() {
        System.out.println("\nВыберете пункт меню:");
        for (int i = 0; i < actions.length; i++) {
            System.out.printf("%2d - %s\n", i + 1, actions[i].getName());
        }
    }

    private int getMenuIndexFromUser() {
        while (true) {
            System.out.print("\nEnter your choice: ");
            if (!sc.hasNextInt()) {
                System.out.println("Значение должно быть целым числом.");
                sc.nextLine();
            } else
                break;
        }
        int choice = sc.nextInt();
        sc.nextLine();
        return choice - 1;
    }

    private boolean validateMenuIndex(int choice) {
        return choice >= 0 && choice < actions.length;
    }
}