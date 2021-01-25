package ua.kiev.prog.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.Utils;

import java.io.IOException;
import java.util.List;

public class ChatRoom {
    private String name;
    private List<String> users;

    public ChatRoom(String name, List<String> users) {
        this.name = name;
        this.users = users;
    }

    public int send() throws IOException {
        return Utils.send("/chatroom", toJSON());
    }

    private String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }
}
