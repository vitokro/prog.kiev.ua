package ua.kiev.prog.msg;

import ua.kiev.prog.Utils;
import ua.kiev.prog.json.Message;
import ua.kiev.prog.users.Data;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FilesServlet extends HttpServlet {

    private static Data data = Data.INSTANCE;
    private MessageList msgList = MessageList.INSTANCE;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        byte[] buf = Utils.requestBodyToArray(req);
        String to = req.getParameter("to");
        String login = req.getParameter("login");
        String fileName = req.getParameter("fileName");
        if (!data.contains(login) || fileName.isEmpty() || fileName.equals("null")
                || to.isEmpty() || to.equals("null")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        addFile(buf, to, login, fileName);
    }

    private void addFile(byte[] buf, String to, String login, String fileName) throws IOException {
        Path dir = Files.createTempDirectory("TempDirForFiles");
        Path file = dir.resolve(fileName);
        try {
            Files.createFile(file);
            Files.write(file, buf);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String msg = String.format("%s wants to send you a file %s. Size is %f Mb\r\n. Write \"file\" to download it",
                login, fileName, 1.0f * buf.length / (1024 * 1024));
        int fileId = file.hashCode();
        msgList.add(new Message(login, to, msg, fileId));
        data.addFile(fileId, file);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int fileId = Integer.parseInt(req.getParameter("fileId"));
        Path file = data.getFile(fileId);
        if (file == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        byte[] bytes = Files.readAllBytes(file);
        try (OutputStream os = resp.getOutputStream()) {
            os.write(bytes);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int fileId = Integer.parseInt(req.getParameter("fileId"));
        Path file = data.getFile(fileId);
        if (file == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        data.delFile(fileId);
        msgList.deleteByFileId(fileId);

        Files.deleteIfExists(file);
        Files.delete(file.getParent());
    }
}
