package ua.kiev.prog.users;

import ua.kiev.prog.Utils;
import ua.kiev.prog.json.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SignupServlet extends HttpServlet {

    private static Data cred = Data.INSTANCE;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = Utils.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        User user = Utils.fromJSON(bufStr, User.class);
        addNewUser(resp, user);
    }

    private boolean addNewUser(HttpServletResponse resp, User user) {
        boolean check = cred.addNewUser(user);
        if (check) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return check;
    }
}
