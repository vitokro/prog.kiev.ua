package ua.kiev.prog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {
    public static final String ANSI_RESET = "\u001B[0m";
    //    public static final String ANSI_BLACK = "\u001B[30m";
//    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    //    public static final String ANSI_CYAN = "\u001B[36m";
//    public static final String ANSI_WHITE = "\u001B[37m";
    private static final String URL = "http://127.0.0.1";
    private static final int PORT = 8080;

    public static String getURL() {
        return URL + ":" + PORT;
    }

    public static int send(String endpoint, String json) throws IOException {
        URL obj = new URL(getURL() + endpoint);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        try {
            os.write(json.getBytes(StandardCharsets.UTF_8));
            return conn.getResponseCode();
        } finally {
            os.close();
        }
    }

    public static int sendFile(String endpoint, byte[] file) throws IOException {
        URL obj = new URL(getURL() + endpoint);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "binary/octet-stream");

        try (OutputStream os = conn.getOutputStream()) {
            os.write(file);
            return conn.getResponseCode();
        }
    }

    public static int downloadFile(String endpoint, Path file) throws IOException {
        URL obj = new URL(getURL() + endpoint);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        final int responseCode = conn.getResponseCode();
        try (InputStream is = conn.getInputStream()) {
            byte[] buf = Utils.responseBodyToArray(is);
            try {
                Files.write(file, buf);
            } catch (IOException e) {
                Utils.printErr("Something went wrong during saving file to your PC");
            }
        }
        return responseCode;
    }

    public static int delFileFromServer(String endpoint) throws IOException {
        URL obj = new URL(getURL() + endpoint);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("DELETE");
        final int responseCode = conn.getResponseCode();
        return conn.getResponseCode();
    }

    public static String sendGetReqWithAnswer(String endpoint) throws IOException {
        URL url = new URL(getURL() + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        InputStream is = conn.getInputStream();
        try {
            byte[] buf = responseBodyToArray(is);
            return new String(buf, StandardCharsets.UTF_8);
        } finally {
            is.close();
        }
    }

    public static void print(String s) {
        System.out.println(s);
    }

    public static void printBlue(String s) {
        print(ANSI_BLUE + s + ANSI_RESET);
    }

    public static void printGreen(String s) {
        print(ANSI_GREEN + s + ANSI_RESET);
    }

    public static void printPurp(String s) {
        System.out.println(ANSI_PURPLE + s + ANSI_RESET);
    }

    public static void printErr(String s) {
        System.err.println(s);
    }

    public static void printYell(String s) {
        print(ANSI_YELLOW + s + ANSI_RESET);
    }


    public static byte[] responseBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }

}
