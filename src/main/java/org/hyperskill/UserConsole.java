package org.hyperskill;

import org.hyperskill.actions.QuitConsole;
import org.hyperskill.menu.AccountMenuOptions;
import org.hyperskill.menu.MainMenuOptions;

import java.util.Scanner;

import static org.hyperskill.menu.AccountMenuOptions.CLOSE_ACCOUNT;
import static org.hyperskill.menu.MainMenuOptions.EXIT;
import static org.hyperskill.menu.MainMenuOptions.getByValue;

public class UserConsole {
    protected static final String CONSOLE_MENU = "1. Create an account\n2. Log into account\n0. Exit";
    protected static final String MENU_ENTER_CARD_NUMBER = "Enter your card number:";
    protected static final String MENU_ENTER_PIN = "Enter your PIN:";
    protected static final String MENU_WRONG_INPUT = "Wrong card number or PIN!";
    protected static final String MENU_LOGIN_SUCCESSFUL = "You have successfully logged in!";
    protected static final String MENU_USER_ACCOUNT =   "1. Balance\n" +
                                                        "2. Add income\n" +
                                                        "3. Do transfer\n" +
                                                        "4. Close account\n" +
                                                        "5. Log out\n" +
                                                        "0. Exit";
    protected static final String MENU_LOGOUT_EXIT = "You have successfully logged out!";

    protected static final String MENU_BALANCE = "Balance: %d";
    protected static final String MENU_CARD_CREATED = "Your card has been created";
    protected static final String MENU_CARD_NUMBER = "Your card number:";
    protected static final String MENU_PIN_NUMBER = "Your card PIN:";
    protected static final String MENU_TRANSFER =   "Transfer\n" +
                                                    "Enter card number:";
    protected static final String MENU_TRANSFER_HOW_MUCH = "Enter how much money you want to transfer:";
    protected static final String MENU_TRANSFER_NO_MONEY = "Not enough money!";
    protected static final String MENU_TRANSFER_SAME_ACCOUNT = "You can't transfer money to the same account!";
    protected static final String MENU_TRANSFER_LUHN_FAILED = "Probably you made mistake in the card number. Please try again!";
    protected static final String MENU_TRANSFER_RECEIVER_VOID = "Such a card does not exist.";
    protected static final String MENU_TRANSFER_INCOME_ADDED = "Income was added!";
    protected static final String MENU_TRANSFER_SUCCESS = "Success!";
    protected static final String MENU_ACCOUNT_CLOSED = "The account has been closed!";
    protected static final String MENU_ADD_INCOME = "Enter income:";

    public static CreditCard createCreditCard(){
        System.out.println(MENU_CARD_CREATED);
        System.out.println(MENU_CARD_NUMBER);
        CreditCard actualCard = CreditCard.createCreditCard();
        Main.creditCardDao.createCard(actualCard);
        System.out.println(actualCard.getCreditCardNumber());
        System.out.println(MENU_PIN_NUMBER);
        System.out.println(actualCard.getPin());
        return actualCard;
    }

    public static void displayMainMenu(Scanner scanner){
        String command = "";
        while (!EXIT.getValue().equals(command)){
            MainMenuOptions option = getByValue(command);
            switch (option){
                case LOGIN -> manageUser(scanner);
                case CREATE_CREDIT_CARD -> createCreditCard();
            }
            System.out.println(CONSOLE_MENU);
            command = scanner.nextLine();
        }
        QuitConsole.exit();
    }

    public static CreditCard attemptLogin(Scanner scanner){
        System.out.println(MENU_ENTER_CARD_NUMBER);
        String inputCardNumber = scanner.nextLine();
        System.out.println(MENU_ENTER_PIN);
        String inputPin = scanner.nextLine();
        return Main.creditCardDao.readCard(inputCardNumber, inputPin);
    }

    public static boolean isLoginSuccessful(CreditCard card){
        return !CreditCard.NULL_CARD.equals(card);
    }

    protected static void manageUser(Scanner scanner){
        CreditCard currentCard = attemptLogin(scanner);
        if (isLoginSuccessful(currentCard)){
            System.out.println(MENU_LOGIN_SUCCESSFUL);
        } else {
            System.out.println(MENU_WRONG_INPUT);
            return;
        }
        String command = "";
        while (!EXIT.getValue().equals(command) && !CLOSE_ACCOUNT.getValue().equals(command)){
            AccountMenuOptions option = AccountMenuOptions.getByValue(command);
            switch (option){
                case BALANCE -> checkBalance(currentCard);
                case ADD_INCOME -> addIncome(scanner, currentCard);
                case DO_TRANSFER -> doTransfer(scanner, currentCard);
                case CLOSE_ACCOUNT -> closeCard(currentCard);
                case LOGOUT -> logout(scanner);

            }
            System.out.println();
            System.out.println(MENU_USER_ACCOUNT);
            command = scanner.nextLine();
        }
        QuitConsole.exit();

    }

    protected static void checkBalance(CreditCard card){
        card = Main.creditCardDao.readCard(card.getCreditCardNumber(), card.getPin());
        int currentBalance = card.getBalance();
        System.out.printf(MENU_BALANCE, currentBalance);
        System.out.println();
    }

    protected static void logout(Scanner scanner){
        System.out.println(MENU_LOGOUT_EXIT);
        displayMainMenu(scanner);
    }

    protected static void addIncome(Scanner scanner, CreditCard card){
        System.out.println(MENU_ADD_INCOME);
        int amount = scanner.nextInt();
        if (card.addIncome(amount)){
            Main.creditCardDao.updateCard(card.getCreditCardNumber(), card.getBalance());
            System.out.println(MENU_TRANSFER_INCOME_ADDED);
        }
    }

    protected static void doTransfer(Scanner scanner, CreditCard card){
        System.out.println(MENU_TRANSFER);
        String inputCardNumber = scanner.nextLine();
        if (!BankUtils.checkLuhnNumber(inputCardNumber)){
            System.out.println(MENU_TRANSFER_LUHN_FAILED);
            return;
        }
        if (!Main.creditCardDao.checkCard(inputCardNumber)){
            System.out.println(MENU_TRANSFER_RECEIVER_VOID);
            return;
        }
        if (card.getCreditCardNumber().equals(inputCardNumber)){
            System.out.println(MENU_TRANSFER_SAME_ACCOUNT);
            return;
        }
        System.out.println(MENU_TRANSFER_HOW_MUCH);
        int amount = scanner.nextInt();
        if (amount > card.getBalance() && amount > 0){
            System.out.println(MENU_TRANSFER_NO_MONEY);
            return;
        }
        if (transferMoney(card.getCreditCardNumber(), inputCardNumber, amount)){
            System.out.println(MENU_TRANSFER_SUCCESS);
        }
    }

    protected static boolean transferMoney(String sourceCard, String targetCard, int amount){
        return Main.creditCardDao.updateCard(sourceCard, amount * -1) &&
                Main.creditCardDao.updateCard(targetCard, amount);
    }

    protected static void closeCard(CreditCard creditCard){
        if(Main.creditCardDao.deleteCard(creditCard.getCreditCardNumber())){
            System.out.println(MENU_ACCOUNT_CLOSED);
        }
    }

}


