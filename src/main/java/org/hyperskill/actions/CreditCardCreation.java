package org.hyperskill.actions;

import org.hyperskill.CreditCard;

public class CreditCardCreation {
    protected static final String MENU_CARD_CREATED = "Your card has been created";
    protected static final String MENU_CARD_NUMBER = "Your card number:";
    protected static final String MENU_PIN_NUMBER = "Your card PIN:";

    public static void createCreditCard(){
        System.out.println(MENU_CARD_CREATED);
        System.out.println(MENU_CARD_NUMBER);
        CreditCard actualCard = CreditCard.createCreditCard();
        CreditCard.registeredCreditCards.add(actualCard);
        System.out.println(actualCard.getCreditCardNumber());
        System.out.println(MENU_PIN_NUMBER);
        System.out.println(actualCard.getPin());
    }
}
