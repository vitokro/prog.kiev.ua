package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public static byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0)
                bos.write(buf, 0, r);
        } while (r != -1);
        return bos.toByteArray();

    }

    public static <T> T fromJSON(String s, Class<T> t) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, t);
    }
}
