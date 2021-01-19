package oopHomeWork11;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GetLinks {
    private String randomUrl;

    public static void main(String[] args) {
//        3. Напишите программу, которая выведет в файл все ссылки,
//которые содержатся в html документе, который будет прислан в
//результате запроса на произвольный URL.
        GetLinks gl = new GetLinks("https://stackoverflow.com/");
        try {
            gl.safeLinksToFile("src/oopHomeWork11/links.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GetLinks() {
    }

    public String getRandomUrl() {
        return randomUrl;
    }

    public void setRandomUrl(String randomUrl) {
        this.randomUrl = randomUrl;
    }

    public GetLinks(String randomUrl) {
        this.randomUrl = randomUrl;
    }

    public void safeLinksToFile(String file) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(file)))){
            String links = linksFromUrl();
            bw.write(links);
        }
    }

    private String linksFromUrl() throws IOException {
        Document doc = Jsoup.connect(randomUrl).get();
        Elements links = doc.select("a[href]");
        StringBuilder sb = new StringBuilder();
        for (Element link : links) {
            sb.append(link.attr("abs:href"))
                    .append(link.text())
                    .append("\n");
        }
        System.out.println(sb);
        return links.toString();
    }
}
