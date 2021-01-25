package ua.kiev.prog.json;

import ua.kiev.prog.json.UserState;

import java.util.List;

// class for getting json data from server
public class AvailableChatsAndUserStates {
    private List<String> chats;
    private List<UserState> userStates;

    public List<String> getChats() {
        return chats;
    }

    public List<UserState> getUserStates() {
        return userStates;
    }
}
