package org.hyperskill.actions;

import org.hyperskill.CreditCard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardCreationTest {

    @Test
    public void shouldCreateCreditCardAddNewCardToCardRegistry(){
        // given
        List<CreditCard> registry = new ArrayList<>(CreditCard.registeredCreditCards);
        int controlSize = registry.size();
        int numberOfCardsToCreate = 1000;

        // when
        for (int i = 0; i < numberOfCardsToCreate; i++){
            CreditCardCreation.createCreditCard();
        }

        // then
        List<CreditCard> actualRegistry = new ArrayList<>(CreditCard.registeredCreditCards);
        assertEquals(controlSize + numberOfCardsToCreate, actualRegistry.size());
    }
}
