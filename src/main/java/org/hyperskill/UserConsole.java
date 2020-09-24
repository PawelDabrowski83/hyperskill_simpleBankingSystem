package org.hyperskill;

import org.hyperskill.actions.CreditCardCreation;
import org.hyperskill.actions.QuitConsole;
import org.hyperskill.menu.AccountMenuOptions;
import org.hyperskill.menu.MainMenuOptions;

import java.util.Scanner;

import static org.hyperskill.menu.MainMenuOptions.EXIT;
import static org.hyperskill.menu.MainMenuOptions.getByValue;

public class UserConsole {
    protected static final String CONSOLE_MENU = "1. Create an account\n2. Log into account\n0. Exit";
    protected static final String MENU_ENTER_CARD_NUMBER = "Enter your card number:";
    protected static final String MENU_ENTER_PIN = "Enter your PIN:";
    protected static final String MENU_WRONG_INPUT = "Wrong card number or PIN!";
    protected static final String MENU_LOGIN_SUCCESSFUL = "You have successfully logged in!";
    protected static final String MENU_USER_ACCOUNT = "1. Balance\n2. Log out\n0. Exit";
    protected static final String MENU_LOGOUT_EXIT = "You have successfully logged out!";
    protected static final String MENU_BALANCE = "Balance: %d";


    public static void displayMainMenu(Scanner scanner){
        String command = "";
        while (!EXIT.getValue().equals(command)){
            MainMenuOptions option = getByValue(command);
            switch (option){
                case LOGIN -> manageUser(scanner);
                case CREATE_CREDIT_CARD -> CreditCardCreation.createCreditCard(scanner);
            }
            System.out.println(CONSOLE_MENU);
            command = scanner.nextLine();
        }
        QuitConsole.exit();
    }



    protected static void manageUser(Scanner scanner){
        String command = "";
        while (!EXIT.getValue().equals(command)){
            AccountMenuOptions option = AccountMenuOptions.getByValue(command);
            switch (option){
                case BALANCE -> checkBalance(scanner);
                case LOGOUT -> logout(scanner);
            }
            System.out.println(MENU_USER_ACCOUNT);
            command = scanner.nextLine();
        }
        QuitConsole.exit();

    }



    protected static void checkBalance(Scanner scanner){
        System.out.println("check balance");
    }

    protected static void logout(Scanner scanner){
        System.out.println("logout");
    }
}


