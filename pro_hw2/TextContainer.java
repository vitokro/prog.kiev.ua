package pro_hw2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SaveTo(path = "src/pro_hw2/annotText.txt")
public class TextContainer {
    private String text = "text";

    public TextContainer() {
    }

    public TextContainer(String text) {
        this.text = text;
    }

    @SaverMethod
    private void save(String file) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file)) ){
            bw.write(text);
        }
    }

    public void save(File file) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file)) ){
            bw.write(text);
        }
    }
}
