package ua.kiev.prog;

import ua.kiev.prog.json.Message;
import ua.kiev.prog.json.User;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private static final StringBuilder mainMenu = new StringBuilder();
    private static final StringBuilder startMenu = new StringBuilder();
    private DownloadedFiles files = DownloadedFiles.INSTANCE;
    static {
      startMenu.append("\nChoose action:\n")
            .append("1) Sign up\n")
            .append("2) Log in\n");

      mainMenu.append("\nChoose action:\n")
            .append("3) Get list of all active users\n")
            .append("4) Send message(use @ for sending private or chatroom msg)\n")
            .append("5) Change your state\n")
            .append("6) Create chatroom\n")
            .append("7) Send file\n")
            .append("0) Log out and exit\n");
    }

    void chooseAction(Scanner scanner) throws IOException, ExitException {
        String login = getLogin(scanner);
        MessageSender ms = new MessageSender(login, scanner);
        ms.getAllUsersAndChats();
        startGetThread(login, this);
        while(true) {
            Utils.print(mainMenu.toString());
            String action = scanner.nextLine();
            switch (action) {
                case "3": // Get list of all active users
                    ms.getAllUsersAndChats();
                    break;
                case "4": // Send private message
                    ms.sendMsg();
                    break;
                case "5": // Edit user's status
                    ms.changeState();
                    break;
                case "6": // Create ChatRoom
                    ms.createChatRoom();
                    break;
                case "7": // Create ChatRoom
                    files.sendFile(scanner, login);
                    break;
                case "0": // Log out
                    ms.logout();
                    break;
                case "file":
                    files.downloadFile(scanner);
                    break;
                default:
                    Utils.print("Please, input action from 3 to 7 or \"file\"");
            }
        }
    }

    private static void startGetThread(String login, Menu menu) {
        Thread th = new Thread(new GetThread(login));
        th.setDaemon(true);
        th.start();
    }

    private static String getLogin(Scanner scanner) throws IOException, ExitException {
        String login = null;
        while(login == null) {
            Utils.print(startMenu.toString());
            String action = scanner.nextLine();
            switch (action) {
                case "1": // 1) Sign up
                    login = signUp(scanner);
                    break;
                case "2": // 2) Log in
                    login = checkLogin(scanner);
                    break;
                case "0": // Exit
                    throw new ExitException();
                default:
                    Utils.print("Please, input action from 0 to 2");
            }
        }
        return login;
    }

    private static String checkLogin(Scanner scanner) throws IOException {
        Utils.print("Enter your login: ");
        String login = scanner.nextLine();

        Utils.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = new User(login, password);
        int resp = Utils.send("/login", user.toJSON());
        if (resp == 200) // 0k
            System.out.println(new Message("Server", "Log in is successful"));
        else {
            System.out.println("Wrong login or password");
            return null;
        }
        return login;
    }

    private static String signUp(Scanner scanner) throws IOException {
        System.out.println("Enter your login: ");
        String login = scanner.nextLine();

        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        User user = new User(login, password);

        int resp = Utils.send("/signup", user.toJSON());
        if (resp == 200) // 0k
            new Message("Server", "User " + login + " has been registered").send();
        else {
            System.out.println(new Message("Server", "User " + login + " is already exists"));
            return null;
        }
        return login;
    }

}
