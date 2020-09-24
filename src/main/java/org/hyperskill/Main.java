package org.hyperskill;

import org.hyperskill.menu.AccountMenuOptions;
import org.hyperskill.menu.MainMenuOptions;

import java.util.Scanner;
import static org.hyperskill.menu.MainMenuOptions.*;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            String command = "";
            while (!EXIT.equals(command)){
                System.out.println(UserConsole.CONSOLE_MENU);
                command = scanner.nextLine();
                int yourChoice = parseInt(command);
            }
        }
    }

    private static int parseInt(String numberAsString){
        try {
            return Integer.parseInt(numberAsString);
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Not a real number tho");
    }
}
