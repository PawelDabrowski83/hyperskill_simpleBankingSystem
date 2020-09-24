package org.hyperskill.actions;

import org.hyperskill.CreditCard;
import org.hyperskill.Main;
import org.hyperskill.UserConsole;

import java.util.Scanner;

public class CreditCardCreation {
    protected static final String MENU_CARD_CREATED = "Your card has been created";
    protected static final String MENU_CARD_NUMBER = "Your card number:";
    protected static final String MENU_PIN_NUMBER = "Your card PIN:";

    public static void createCreditCard(Scanner scanner){
        System.out.println(MENU_CARD_CREATED);
        System.out.println(MENU_CARD_NUMBER);
        CreditCard actualCard = CreditCard.createCreditCard();
        Main.registeredCreditCards.add(actualCard);
        System.out.println(actualCard.getCreditCardNumber());
        System.out.println(MENU_PIN_NUMBER);
        System.out.println(actualCard.getPin());
        UserConsole.displayMainMenu(scanner);
    }
}
