package org.hyperskill;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreditCardDaoIntegrationTest {

    @Test
    public void shouldCreateCardAndReadWork() throws SQLException {
        CreditCardDao creditCardDao = new CreditCardDao();
        assertTrue(creditCardDao.createDatabase());

        CreditCard card = CreditCard.createCreditCard();
        assertTrue(creditCardDao.createCard(card));

        CreditCard targetCard = creditCardDao.readCard(card.getCreditCardNumber(), card.getPin());
        System.out.printf("%s - %s", card.getCreditCardNumber(), card.getPin());
        assertEquals(card, targetCard);
        assertEquals(card.getCreditCardNumber(), targetCard.getCreditCardNumber());
        assertEquals(card.getPin(), targetCard.getPin());
    }
}
