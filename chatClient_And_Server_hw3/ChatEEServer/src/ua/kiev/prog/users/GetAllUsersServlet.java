package ua.kiev.prog.users;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetAllUsersServlet  extends HttpServlet {

    private Data data = Data.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        if (!data.contains(login)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        resp.setContentType("application/json");
        String json = new Gson().toJson(data.getAllUsersAndChats(login));
        if (json != null) {
            PrintWriter pw = resp.getWriter();
            pw.print(json);
        }
    }
}
