package ua.kiev.prog.users;

import ua.kiev.prog.Utils;
import ua.kiev.prog.json.ChatRoom;
import ua.kiev.prog.users.Data;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CreateChatRoomServlet extends HttpServlet {

    private static Data data = Data.INSTANCE;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        byte[] buf = Utils.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        ChatRoom room = Utils.fromJSON(bufStr, ChatRoom.class);

        data.createChatRoom(room.getName(), room.getUsers());
    }

}
