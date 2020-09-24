package org.hyperskill;

import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreditCard {
    static final Logger logger = LoggerFactory.getLogger(CreditCard.class);

    protected static final String BIN_PREFIX = "400000";
    protected static final int CREDIT_CARD_NUMBER_LENGTH = 16;
    protected static Pattern CREDIT_CARD_NUMBER_FORMULA = Pattern.compile("^400000\\d{10}$");

    private final String creditCardNumber;
    private String pin;

    private CreditCard(String creditCardNumber, String pin) {
        logger.info("Creating CreditCard instance with creditCardNumber: {} and pin: {}", creditCardNumber, pin);
        this.creditCardNumber = creditCardNumber;
        this.pin = pin;
    }

    public CreditCard createCreditCard(){
        return this;
    }

    protected static String createCreditCardNumber(){
        String creditCardNumber = BIN_PREFIX + BankUtils.createRandomNumbers(10);
        logger.debug("Creating credit card number: {}", creditCardNumber);
        return creditCardNumber;
    }

    protected static boolean validateCreditCardNumber(String creditCardNumber){
        logger.debug("Validating credit card number {}", creditCardNumber);
        if (creditCardNumber == null || creditCardNumber.isBlank()){
            logger.debug("Given number is null or blank");
            return false;
        }
        boolean result = CREDIT_CARD_NUMBER_FORMULA.matcher(creditCardNumber).matches();
        logger.debug("Validation complete: {}", result);
        return result;
    }
}
