package ua.kiev.prog;

public class Menu {
    private static final StringBuilder mainMenu = new StringBuilder();
    private static final StringBuilder startMenu = new StringBuilder();

    static {
      startMenu.append("\nChoose action:\n")
            .append("1) Sign up\n")
            .append("2) Log in\n");

      mainMenu.append("\nChoose action:\n")
            .append("3) Get list of all active users\n")
            .append("4) Send message(use @ for sending private or chatroom msg)\n")
            .append("5) Change your state\n")
            .append("6) Create chatroom\n")
            .append("7) Log out and exit\n");
    }

    public static String getMainMenu(){
        return mainMenu.toString();
    }

    public static String getStartMenu(){
        return startMenu.toString();
    }
}
