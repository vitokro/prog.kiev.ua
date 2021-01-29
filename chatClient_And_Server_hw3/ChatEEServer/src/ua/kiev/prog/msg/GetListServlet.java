package ua.kiev.prog.msg;

import ua.kiev.prog.users.Data;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetListServlet extends HttpServlet {
	
	private MessageList msgList = MessageList.INSTANCE;
	private static Data data = Data.INSTANCE;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String fromStr = req.getParameter("from");
		String login = req.getParameter("login");
		if (!data.contains(login)) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		int from = 0;
		try {
			from = Integer.parseInt(fromStr);
			if (from < 0)
				from = 0;
		} catch (Exception ex) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
		}

		resp.setContentType("application/json");
		
		String json = msgList.toJSON(login, from);
		if (json != null) {
			try (OutputStream os = resp.getOutputStream()) {
				byte[] buf = json.getBytes(StandardCharsets.UTF_8);
				os.write(buf);
			}
		}
	}
}
