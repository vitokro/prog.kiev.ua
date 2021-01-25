package ua.kiev.prog.users;

import ua.kiev.prog.Utils;
import ua.kiev.prog.json.User;
import ua.kiev.prog.json.Message;
import ua.kiev.prog.msg.MessageList;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LoginServlet extends HttpServlet {

    private static Data data = Data.INSTANCE;
    private MessageList msgList = MessageList.INSTANCE;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        byte[] buf = Utils.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);
        User user = Utils.fromJSON(bufStr, User.class);
        checkUser(resp, user);
            
    }

    private boolean checkUser(HttpServletResponse resp, User user) {
        boolean check = data.tryLogin(user);
        if (check) {
            resp.setStatus(HttpServletResponse.SC_OK);
            msgList.add(new Message("Server", "User " + user.getLogin() + " logged in"));
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return check;
    }


}
