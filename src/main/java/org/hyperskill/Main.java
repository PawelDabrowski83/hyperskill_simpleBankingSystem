package org.hyperskill;

import org.hyperskill.menu.AccountMenuOptions;
import org.hyperskill.menu.MainMenuOptions;

import java.util.Scanner;
import static org.hyperskill.menu.MainMenuOptions.*;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            displayMainMenu(scanner);
        }
    }

    private static void displayMainMenu(Scanner scanner){
        String command = "";
        while (!EXIT.getValue().equals(command)){
            MainMenuOptions option = getByValue(command);
            switch (option){
                case LOGIN -> manageUser(scanner);
                case CREATE_ACCOUNT -> createAccount(scanner);
            }
            System.out.println(UserConsole.CONSOLE_MENU);
            command = scanner.nextLine();
        }
        quitConsole();
    }

    private static void quitConsole(){
        System.out.println(UserConsole.MENU_QUIT);
        System.exit(0);
    }

    protected static void manageUser(Scanner scanner){
        String command = "";
        while (!EXIT.getValue().equals(command)){
            AccountMenuOptions option = AccountMenuOptions.getByValue(command);
            switch (option){
                case BALANCE -> checkBalance(scanner);
                case LOGOUT -> logout(scanner);
            }
            System.out.println(UserConsole.MENU_USER_ACCOUNT);
            command = scanner.nextLine();
        }
        quitConsole();

    }

    protected static void createAccount(Scanner scanner){
        System.out.println("create account");
    }

    protected static void checkBalance(Scanner scanner){
        System.out.println("check balance");
    }

    protected static void logout(Scanner scanner){
        System.out.println("logout");
    }

}
