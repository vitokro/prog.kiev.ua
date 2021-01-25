package ua.kiev.prog.json;

import java.util.List;

public class AvailableChatsAndUserStates {
    private List<String> chats;
    private List<UserState> userStates;

    public AvailableChatsAndUserStates(List<String> chats, List<UserState> userStates) {
        this.chats = chats;
        this.userStates = userStates;
    }

    public List<String> getChats() {
        return chats;
    }

    public void setChats(List<String> chats) {
        this.chats = chats;
    }

    public List<UserState> getUserStates() {
        return userStates;
    }

    public void setUserStates(List<UserState> userStates) {
        this.userStates = userStates;
    }
}
