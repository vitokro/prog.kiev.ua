package ua.kiev.prog;

import com.google.gson.Gson;
import ua.kiev.prog.json.AvailableChatsAndUserStates;
import ua.kiev.prog.json.ChatRoom;
import ua.kiev.prog.json.Message;
import ua.kiev.prog.json.UserState;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class MessageSender {
    private String login;
    private Scanner scanner;
    private AvailableChatsAndUserStates usersAndChats;

    public MessageSender(String login, Scanner scanner) {
        this.login = login;
        this.scanner = scanner;
    }

    public void getAllUsersAndChats() throws IOException {
        String users = Utils.sendGetReqWithAnswer("/get-all-users-chats?login=" + login);
        Gson json = new Gson();

        usersAndChats = json.fromJson(users, AvailableChatsAndUserStates.class);
        Utils.print("List of all users and their states:");
        usersAndChats.getUserStates()
                .stream()
                .forEach(userState -> Utils.printYell("User " + userState.getLogin() + userState.getState()));
        Utils.print("List of all your chatrooms:");
        usersAndChats.getChats().forEach(Utils::printYell);
        System.out.println();
    }

    public void sendMsg() throws IOException {
        String to = null;
        String text;
        boolean isChat = false;
        do {
            text = inputText();

            int iAt = text.indexOf("@"); // index of @
            if (iAt != -1) {
                int iWS = text.indexOf(" "); // index of first whitespace
                to = text.substring(iAt + 1, iWS);
                text = text.substring(iWS + 1);
            } else
                break;
            final String finalTo = to;
            boolean isUser = usersAndChats.getUserStates()
                    .stream()
                    .anyMatch(userState -> userState.getLogin().equals(finalTo));
            if (!isUser)
                isChat = usersAndChats.getChats().contains(finalTo);
            if (isChat || isUser)
                break;
            Utils.print("No such user or chatroom as " + to);
        } while (true);

        internalSendMsg(to, text, isChat);
    }

    private String inputText() {
        String text = "";
        Utils.print("Enter your message: ");
        while (text.isEmpty()) {
            text = scanner.nextLine();
        }
        text = text.trim();
        return text;
    }

    private void internalSendMsg(String to, String text, boolean isChat) throws IOException {
        Message m;
        if (isChat)
            m = new Message(login, to, text, to);
        else
            m = new Message(login, to, text);
        int resp = m.send();
        if (resp != 200) { // 200 OK
            System.out.println("HTTP error occurred: " + resp);
            return;
        }
    }

    public void changeState() throws IOException {
        Utils.print("Type 1 for active state or 2 for inactive state:");
        String state = "";
        while (!(state.equals("1") || state.equals("2"))) {
            state = scanner.nextLine();
        }
        String stateFull = state.equals("1") ? " state: active" : " state: inactive";
        new UserState(login, stateFull).send();
        Utils.print("Your state changed to" + stateFull);
    }

    public void logout() throws IOException, ExitException {
        new UserState(login, " state: logged out").send();
        throw new ExitException();
    }

    public void createChatRoom() throws IOException {
        Utils.print("Type nicknames who you want to add to this chatroom, separated by whitespace:");
        String str = "";
        while (str.isEmpty()) {
            str = scanner.nextLine();
        }
        str += " " + login;
        List<String> users = List.of(str.split(" "));

        Utils.print("Type name of chatroom");
        String name = "";
        while (name.isEmpty()) {
            name = scanner.nextLine();
        }
        new ChatRoom(name, users).send();
    }



    public String getLogin() {
        return login;
    }

    public void downloadFile() {
    }
}
