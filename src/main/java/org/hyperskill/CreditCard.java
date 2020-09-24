package org.hyperskill;

import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreditCard {
    static final Logger logger = LoggerFactory.getLogger(CreditCard.class);

    protected static final String BIN_PREFIX = "400000";
    protected static final int CREDIT_CARD_NUMBER_LENGTH = 16;
    protected static final int PIN_NUMBER_LENGTH = 4;
    protected static Pattern CREDIT_CARD_NUMBER_FORMULA = Pattern.compile("^400000\\d{10}$");
    protected static Pattern PIN_NUMBER_FORMULA = Pattern.compile("^\\d{4}$");

    private final String creditCardNumber;
    private String pin;

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getPin() {
        return pin;
    }

    private CreditCard(String creditCardNumber, String pin) {
        logger.info("Creating CreditCard instance with creditCardNumber: {} and pin: {}", creditCardNumber, pin);
        this.creditCardNumber = creditCardNumber;
        this.pin = pin;
    }

    public CreditCard createCreditCard(){
        return this;
    }

    protected static String createCreditCardNumber(){
        String creditCardNumber = BIN_PREFIX + BankUtils.createRandomNumbers(CREDIT_CARD_NUMBER_LENGTH - BIN_PREFIX.length());
        logger.debug("Creating credit card number: {}", creditCardNumber);
        return creditCardNumber;
    }

    protected static String createPinNumber(){
        String pinNumber = BankUtils.createRandomNumbers(PIN_NUMBER_LENGTH);
        logger.debug("Creating pin number: {}", pinNumber);
        return pinNumber;
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

    protected static boolean validatePinNumber(String pinNumber){
        if (pinNumber == null || pinNumber.isBlank()){
            return false;
        }
        return PIN_NUMBER_FORMULA.matcher(pinNumber).matches();
    }
}
