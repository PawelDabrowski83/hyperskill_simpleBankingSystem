package org.hyperskill;

import java.util.Scanner;

public class Main {
    public static final CreditCardDao creditCardDao = new CreditCardDao();

    public static void main(String[] args) {
        creditCardDao.createDatabase();
        try (Scanner scanner = new Scanner(System.in)){
            UserConsole.displayMainMenu(scanner);
        }
    }



}
