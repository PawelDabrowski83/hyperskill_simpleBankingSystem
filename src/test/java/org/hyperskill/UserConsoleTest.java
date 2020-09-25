package org.hyperskill;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserConsoleTest {

    @Test
    public void shouldCreateCreditCardAddNewCardToCardRegistry(){
        // given
        List<CreditCard> registry = new ArrayList<>(CreditCard.registeredCreditCards);
        int controlSize = registry.size();
        int numberOfCardsToCreate = 1000;

        // when
        for (int i = 0; i < numberOfCardsToCreate; i++){
            UserConsole.createCreditCard();
        }

        // then
        List<CreditCard> actualRegistry = new ArrayList<>(CreditCard.registeredCreditCards);
        assertEquals(controlSize + numberOfCardsToCreate, actualRegistry.size());
        for (CreditCard creditCard : actualRegistry){
            assertTrue(CreditCard.validateCreditCardNumber(creditCard.getCreditCardNumber()));
            assertTrue(CreditCard.validatePinNumber(creditCard.getPin()));
        }
    }

    @Test
    public void shouldIsLoginSuccessfulGivenNullCardReturnFalse(){
        // given
        CreditCard card = CreditCard.NULL_CARD;

        // then
        assertFalse(UserConsole.isLoginSuccessful(card));
    }

    @Test
    public void shouldIsLoginSuccessfulGivenValidCardReturnTrue(){
        // given
        CreditCard card = UserConsole.createCreditCard();

        // then
        assertTrue(UserConsole.isLoginSuccessful(card));
    }
}
