package org.hyperskill;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final List<CreditCard> registeredCreditCards = new ArrayList<>();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            UserConsole.displayMainMenu(scanner);
        }
    }



}
