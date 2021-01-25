package ua.kiev.prog.users;

import ua.kiev.prog.Utils;
import ua.kiev.prog.json.UserState;
import ua.kiev.prog.json.Message;
import ua.kiev.prog.msg.MessageList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class StateServlet extends HttpServlet {

    private static Data data = Data.INSTANCE;
    private MessageList msgList = MessageList.INSTANCE;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        byte[] buf = Utils.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);
        UserState userState = Utils.fromJSON(bufStr, UserState.class);

        data.setStatus(userState);
        if (" state: logged out".equals(userState.getState())) {
            msgList.add(new Message("Server", "User " + userState.getLogin() + " logged out"));
        }

    }
}
