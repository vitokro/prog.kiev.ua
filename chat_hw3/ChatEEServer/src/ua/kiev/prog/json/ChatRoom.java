package ua.kiev.prog.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class ChatRoom {
    private String name;
    private List<String> users;

    public ChatRoom(String name, List<String> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
