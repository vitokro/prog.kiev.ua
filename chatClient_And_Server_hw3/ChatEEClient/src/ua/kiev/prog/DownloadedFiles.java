package ua.kiev.prog;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.InvalidPathException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public enum DownloadedFiles {
    INSTANCE;

    private final ConcurrentLinkedQueue<Integer> fileIds = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<String> fileNames = new ConcurrentLinkedQueue<>();

    private int internalDownloadFile(String endpoint, File file) throws IOException {
        URL obj = new URL(Utils.getURL() + endpoint);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        final int responseCode = conn.getResponseCode();

        try (BufferedInputStream is = new BufferedInputStream(conn.getInputStream());
             FileOutputStream bos = new FileOutputStream(file)) {
            byte[] buf = new byte[10240];
            int r;
            do {
                r = is.read(buf);
                if (r > 0) {
                    bos.write(buf, 0, r);
                    bos.flush();
                }
            } while (r != -1);
        } catch (IOException e) {
            Utils.printErr("Something went wrong during saving file to your PC");
        }
        return responseCode;
    }

    public void downloadFile(Scanner scanner) throws IOException {
        String fileName;
        int fileId;
        try {
            fileName = fileNames.poll();
            fileId = fileIds.poll();
        } catch (Exception e) {
            Utils.printErr("No files for download!");
            return;
        }
        Utils.print("Type full path for downloading file");
        String fullFilePath = scanner.nextLine() + fileName;
        int respCode = 0;
        File file = new File(fullFilePath);
        try {
            respCode = internalDownloadFile("/files?fileId=" + fileId, file);
        } catch (IOException e) {
            Utils.printErr("Download failed: \n\r" + e.getMessage());
            Utils.printErr("Server's response code: " + respCode);
        }
        if ((respCode == 200)) {
            Utils.print("Success! Check out downloaded file: \n" + file.toString());
            delFileFromServer("/files?fileId=" + fileId);
        }
    }

    public void sendFile(Scanner scanner, String login) throws IOException {
        Utils.print("Type @nickname + full path to file you want to send");
        String str = "";
        while (str.isEmpty()) {
            str = scanner.nextLine();
        }

        String to;
        int iAt = str.indexOf("@"); // index of @
        if (iAt != -1) {
            int iWS = str.indexOf(" "); // index of first whitespace
            to = str.substring(iAt + 1, iWS);
            str = str.substring(iWS + 1);
        } else {
            Utils.printErr("No nickname found");
            return;
        }

        File file;
        try {
            file = new File(str);
        } catch (InvalidPathException e) {
            Utils.printErr("Your path to file is not valid! File not sent");
            return;
        }
        int respCode = internalSendFile("/files?login=" + login + "&to=" + to + "&fileName=" +
                URLEncoder.encode(file.getName(), StandardCharsets.UTF_8), file);
        if (respCode == 200) {
            Utils.print("Your file have been sent successfully");
        } else {
            Utils.printErr("Problems with sending your file! File not sent");
            Utils.printErr("Server's response code: " + respCode);
        }
    }

    private int internalSendFile(String endpoint, File file) throws IOException {
        HttpURLConnection conn = Utils.getHttpURLConnection(endpoint);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setChunkedStreamingMode(10240);
        conn.setRequestProperty("Content-Type", "multipart/form-data");

        try (BufferedOutputStream os = new BufferedOutputStream(conn.getOutputStream());
             FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[10240];
            int byteread;
            while ((byteread = fis.read(buffer)) > 0) {
                os.write(buffer, 0, byteread);
                os.flush();
            }
            return conn.getResponseCode();
        }
    }

    private void delFileFromServer(String endpoint) throws IOException {
        HttpURLConnection conn = Utils.getHttpURLConnection(endpoint);
        conn.setRequestMethod("DELETE");
    }

    public void setFile(int fileId, String fileName) {
        this.fileNames.add(fileName);
        this.fileIds.add(fileId);
    }
}
