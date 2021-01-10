package oopHomeWork9.letters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class CountLetters {
    private File file;
    private boolean caseInsensitive = false;

    public CountLetters(String file, boolean caseInsensitive) {
        this.file = new File(file);
        this.caseInsensitive = caseInsensitive;
    }

    public CountLetters() {
    }
    // списав частину рішення з конспекта, але відчуваю, що можна було зробити краще,
    // але так і не придумав як
    public void countWithStreamApi() throws IOException {
       var list = Files.lines(file.toPath())
               .map(n -> caseInsensitive ? n.toUpperCase() : n)
               .flatMapToInt(String::chars)
               .filter(n -> Character.isLetter(n))
               .mapToObj((n) -> (Character.valueOf((char) n)))
               .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
               .entrySet()
               .stream()
               .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
               .collect(Collectors.toList());
       System.out.println(list);
   }
    public void count() throws IOException {

        try (FileReader fr = new FileReader(file)) {
            char[] buffer = new char[1024];
            int bytes;
            List<LatinLetter> list = new ArrayList<>();
            while((bytes = fr.read(buffer)) > 0){
                for (int i = 0; i < bytes; i++) {
                    if (Character.isLetter(buffer[i])) {
                        LatinLetter let;
                        if (caseInsensitive)
                            let = new LatinLetter(Character.toLowerCase(buffer[i]));
                        else
                            let = new LatinLetter(buffer[i]);
                        if (!list.contains(let)) {
                            let.incQuant();
                            list.add(let);
                        }
                        else
                            list.get(list.indexOf(let)).incQuant();
                    }
                }
            }
            list.sort((o1, o2) -> o2.getQuantity() - o1.getQuantity());
            System.out.println(list);
        }
    }
    public File getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = new File(file);
    }

    public boolean isCaseInsensitive() {
        return caseInsensitive;
    }

    public void setCaseInsensitive(boolean caseInsensitive) {
        this.caseInsensitive = caseInsensitive;
    }

    @Override
    public String toString() {
        return "CountLetters{" +
                "file=" + file +
                ", caseInsensitive=" + caseInsensitive +
                '}';
    }
}
