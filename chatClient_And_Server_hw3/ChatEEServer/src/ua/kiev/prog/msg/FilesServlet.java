package ua.kiev.prog.msg;

import ua.kiev.prog.json.Message;
import ua.kiev.prog.users.Data;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesServlet extends HttpServlet {

    private static final Data data = Data.INSTANCE;
    private final MessageList msgList = MessageList.INSTANCE;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        File tmpFile = downloadFile(req);
        String to = req.getParameter("to");
        String login = req.getParameter("login");
        String fileName = req.getParameter("fileName");

        if (!data.contains(login) || fileName.isEmpty() || fileName.equals("null")
                || to.isEmpty() || to.equals("null")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        File file = new File(tmpFile.getParentFile().getPath() + "\\" + fileName);
        tmpFile.renameTo(file);
        String msg = String.format("%s wants to send you a file %s. Size is %file Mb\r\n. Write \"file\" to download it",
                login, fileName, 1.0f * file.length() / (1024 * 1024));
        int fileId = file.hashCode();
        msgList.add(new Message(login, to, msg, fileId));
        data.addFile(fileId, file);
    }

    private File downloadFile(HttpServletRequest req) throws IOException {
        Path dir = Files.createTempDirectory("TempFiles");
        File tmpFile = dir.resolve("temp.tmp").toFile();

        byte[] buf;
        try (BufferedInputStream is = new BufferedInputStream(req.getInputStream());
             FileOutputStream bos = new FileOutputStream(tmpFile)) {
            buf = new byte[10240];
            int r;
            do {
                r = is.read(buf);
                if (r > 0) {
                    bos.write(buf, 0, r);
                    bos.flush();
                }
            } while (r != -1);
        }
        return tmpFile;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int fileId = Integer.parseInt(req.getParameter("fileId"));
        File file = data.getFile(fileId);
        if (file == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        resp.setContentType("multipart/form-data");
        upLoadFile(resp, file);
    }

    private void upLoadFile(HttpServletResponse resp, File file) throws IOException {
        try (BufferedOutputStream os = new BufferedOutputStream(resp.getOutputStream());
             FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[10240];
            int byteread = 0;
            for (; (byteread = fis.read(buffer)) > 0; ) {
                os.write(buffer, 0, byteread);
                os.flush();
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int fileId = Integer.parseInt(req.getParameter("fileId"));
        File file = data.getFile(fileId);
        if (file == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        data.delFile(fileId);
        msgList.deleteByFileId(fileId);

        file.delete(); // delete file on server
        file.getParentFile().delete(); // and temp dir
    }
}
