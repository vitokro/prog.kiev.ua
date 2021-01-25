package ua.kiev.prog.msg;

import ua.kiev.prog.Utils;
import ua.kiev.prog.json.Message;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet {

	private MessageList msgList = MessageList.INSTANCE;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		byte[] buf = Utils.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

		Message msg = Utils.fromJSON(bufStr, Message.class);

		if (msg != null)
			msgList.add(msg);
		else
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}


}
