package oopHomeWork11;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckUrlsFromFile {
    private String file;

    public static void main(String[] args) {
//        1. Проверить доступность сайтов указанных в отдельном файле.
        CheckUrlsFromFile checkUrlsFromFile = new CheckUrlsFromFile("src/oopHomeWork11/links.txt");
        try {
            checkUrlsFromFile.check();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public CheckUrlsFromFile(String file) {
        this.file = file;
    }

    public CheckUrlsFromFile() {
    }

    public void check() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String link;
            while ((link = br.readLine()) != null){
                if (internalCheckUrl(link)) {
                    System.out.println(link + " is OK");
                } else {
                    System.err.println(link + " is not OK");
                }
            };
        }
    }

    private boolean internalCheckUrl(String link)  {
        boolean result;
        try {
            URL url = new URL(link);
            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            result = urlc.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            return false;
        }
        return result;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
