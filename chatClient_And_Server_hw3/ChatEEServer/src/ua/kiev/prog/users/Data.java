package ua.kiev.prog.users;

import ua.kiev.prog.json.AvailableChatsAndUserStates;
import ua.kiev.prog.json.User;
import ua.kiev.prog.json.UserState;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.util.stream.Collectors.toList;

public enum Data {
    INSTANCE;

    private static final ConcurrentMap<String, String> credentials = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, String> usersStates = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, List<String>> chatRooms = new ConcurrentHashMap<>();
    private static final ConcurrentMap<Integer, File> files = new ConcurrentHashMap<>();

    public boolean tryLogin(User user) {
        String login = user.getLogin();
        boolean successLogin = Objects.equals(credentials.get(login), user.getPassword());
        if (successLogin) {
            usersStates.put(login, " state: active");
        }
        return successLogin;
    }

    public boolean contains(String login) {
        return credentials.containsKey(login);
    }

    public boolean addNewUser(User user) {
        String login = user.getLogin();
        if (credentials.putIfAbsent(login, user.getPassword()) == null) {
            usersStates.putIfAbsent(login, " state: active");
            chatRooms.put(login, new ArrayList<>());
            return true;
        }
        return false;
    }

    public void setStatus(UserState st) {
        usersStates.put(st.getLogin(), st.getState());
    }

    public void createChatRoom(String name, List<String> logins) {
        logins.forEach(l -> chatRooms.get(l).add(name));
    }

    public List<String> getChatRooms(String login) {
        return chatRooms.get(login);
    }


    public AvailableChatsAndUserStates getAllUsersAndChats(String login) {
        List<String> chats = chatRooms.entrySet().stream()
                .filter(entry -> entry.getKey().equals(login))
                .flatMap(entry -> entry.getValue().stream())
                .collect(toList());
        List<UserState> userStates = usersStates.entrySet().stream()
                .map(entry -> new UserState(entry.getKey(), entry.getValue()))
                .collect(toList());
        return new AvailableChatsAndUserStates(chats, userStates);
    }

    public void addFile(int fileId, File file) {
        files.put(fileId, file);
    }

    public File getFile(int fileId) {
        return files.get(fileId);
    }

    public void delFile(int fileId) {
        files.remove(fileId);
    }
}
