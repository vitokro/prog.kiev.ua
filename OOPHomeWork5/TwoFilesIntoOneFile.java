package OOPHomeWork5;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TwoFilesIntoOneFile {
    public TwoFilesIntoOneFile() {
    }

    public static File copySameWords(File f1, File f2) throws IOException {
        String[] f1words;
        String[] f2words;
        try (FileInputStream fis1 = new FileInputStream(f1);
             FileInputStream fis2 = new FileInputStream(f2)   ) {
            f1words = new String(fis1.readAllBytes()).split("[\n\r.;,:!@#$%&*()-+=\"{} ^/_]{1,}");
            f2words = new String(fis2.readAllBytes()).split("[\n\r.;,:!@#$%&*()-+=\"{} ^/_]{1,}");
        }
        Set<String> f1set = new HashSet<>();
        Collections.addAll(f1set, f1words);
        Set<String> f2set = new HashSet<>();
        Collections.addAll(f2set, f2words);
        f1set.retainAll(f2set);
        StringBuilder sb = new StringBuilder();
        for(String word: f1set){
            sb.append(word);
            sb.append(" ");
        }
        File newFile = new File("SameWords.txt");
        try (FileWriter fw = new FileWriter(newFile)) {
            fw.write(sb.toString());
        }
        return newFile;
    }

}
