package ua.kiev.prog.json;


public class UserState {
    private String login;
    private String state;

    public UserState(String login, String state) {
        this.login = login;
        this.state = state;
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
