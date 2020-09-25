package org.hyperskill;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            UserConsole.displayMainMenu(scanner);
        }
    }



}
