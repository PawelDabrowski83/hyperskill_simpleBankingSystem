package org.hyperskill;

import org.hyperskill.menu.AccountMenuOptions;
import org.hyperskill.menu.MainMenuOptions;

import java.util.Scanner;
import static org.hyperskill.menu.MainMenuOptions.*;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            String command = "";
            while (!EXIT.getValue().equals(command)){
                System.out.println(UserConsole.CONSOLE_MENU);
                command = scanner.nextLine();
            }
            System.out.println(UserConsole.MENU_QUIT);
        }
    }

}
