package org.hyperskill;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class CreditCard {
    public static final CreditCard NULL_CARD = new CreditCard(null, null);

    protected static final String BIN_PREFIX = "400000";
    protected static final int CREDIT_CARD_NUMBER_LENGTH = 16;
    protected static final int PIN_NUMBER_LENGTH = 4;
    protected static Pattern CREDIT_CARD_NUMBER_FORMULA = Pattern.compile("^400000\\d{10}$");
    protected static Pattern PIN_NUMBER_FORMULA = Pattern.compile("^\\d{4}$");

    public static final List<CreditCard> registeredCreditCards = new ArrayList<>();
    private final String creditCardNumber;
    private final String pin;
    private int balance;

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getPin() {
        return pin;
    }

    public int getBalance() {
        return balance;
    }

    private CreditCard(String creditCardNumber, String pin) {
        this.creditCardNumber = creditCardNumber;
        this.pin = pin;
        this.balance = 0;
    }

    public static CreditCard createCreditCard(){
        return new CreditCard(createCreditCardNumber(), createPinNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(creditCardNumber, that.creditCardNumber) &&
                Objects.equals(pin, that.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditCardNumber, pin);
    }

    protected static String createCreditCardNumber(){
        return BIN_PREFIX + BankUtils.createRandomNumbers(CREDIT_CARD_NUMBER_LENGTH - BIN_PREFIX.length());
    }

    protected static String createPinNumber(){
        return BankUtils.createRandomNumbers(PIN_NUMBER_LENGTH);
    }

    public static boolean validateCreditCardNumber(String creditCardNumber){
        if (creditCardNumber == null || creditCardNumber.isBlank()){
            return false;
        }
        return CREDIT_CARD_NUMBER_FORMULA.matcher(creditCardNumber).matches();
    }

    public static boolean validatePinNumber(String pinNumber){
        if (pinNumber == null || pinNumber.isBlank()){
            return false;
        }
        return PIN_NUMBER_FORMULA.matcher(pinNumber).matches();
    }

    public static CreditCard getCardIfExist(String number, String pin){
        if (registeredCreditCards.isEmpty()){
            return NULL_CARD;
        }
        CreditCard searchedCard = new CreditCard(number, pin);
        if (registeredCreditCards.contains(searchedCard)){
            return registeredCreditCards.get(registeredCreditCards.indexOf(searchedCard));
        }
        return NULL_CARD;
    }
}
