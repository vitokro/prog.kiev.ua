package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.json.JsonMessages;
import ua.kiev.prog.json.Message;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetThread implements Runnable {
    private final Gson gson = new GsonBuilder().create();
    private int n;
    private String login;

    public GetThread(String login) {
        this.login = login;
    }

    @Override
    public void run() {
        try {
            while ( ! Thread.interrupted()) {
                URL url = new URL(Utils.getURL() + "/get?from=" + n + "&login=" + login);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();

                InputStream is = http.getInputStream();
                try {
                    byte[] buf = Utils.responseBodyToArray(is);
                    String strBuf = new String(buf, StandardCharsets.UTF_8);

                    JsonMessages list = gson.fromJson(strBuf, JsonMessages.class);
                    if (list != null) {
                        for (Message m : list.getList()) {
                            if ("Server".equals(m.getFrom()))
                                Utils.printGreen(m.toString());
                            else if (login.equals(m.getFrom()))
                                Utils.printBlue(m.toString());
                            else if (login.equals(m.getTo()))
                                Utils.printPurp(m.toString());
                            else if ("All".equals(m.getTo()))
                                Utils.printYell(m.toString());
                            else
                                Utils.print(m.toString());
                            n++;
                        }
                    }
                } finally {
                    is.close();
                }
                Thread.sleep(500);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
