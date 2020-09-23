package org.hyperskill;

import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Account {
    static final Logger logger = LoggerFactory.getLogger(Account.class);

    protected static final String BIN_PREFIX = "400000";
    protected static final int ACCOUNT_NUMBER_LENGTH = 16;
    protected static Pattern ACCOUNT_NUMBER_FORMULA = Pattern.compile("^400000\\d{10}$");

    private final String accountNumber;
    private String pin;

    private Account(String accountNumber, String pin) {
        logger.info("Creating Account instance with accountNumber: {} and pin: {}", accountNumber, pin);
        this.accountNumber = accountNumber;
        this.pin = pin;
    }

    public Account createAccount(){
        return this;
    }

    protected static boolean validateAccountNumber(String accountNumber){
        logger.debug("Validating account number {}", accountNumber);
        if (accountNumber == null || accountNumber.isBlank()){
            logger.debug("Given number is null or blank");
            return false;
        }
        boolean result = ACCOUNT_NUMBER_FORMULA.matcher(accountNumber).matches();
        logger.debug("Validation complete: {}", result);
        return result;
    }
}
