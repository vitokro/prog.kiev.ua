package ua.kiev.prog.json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.Utils;

import java.io.IOException;

public class UserState {
    private String login;
    private String state;

    public UserState(String login, String state) {
        this.login = login;
        this.state = state;
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public int send() throws IOException {
        return Utils.send("/state", toJSON());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
