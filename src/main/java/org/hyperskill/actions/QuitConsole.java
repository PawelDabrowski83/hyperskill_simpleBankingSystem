package org.hyperskill.actions;

public class QuitConsole {
    protected static final String MENU_QUIT = "Bye!";

    public static void exit(){
        System.out.println(MENU_QUIT);
        System.exit(0);
    }
}
