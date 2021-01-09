package oopHomeWork9.letters;


import java.io.IOException;

public class Main {

    public static void main(String[] args) {
//3) Считайте из файла текст на английском языке, вычислите
//относительную частоту повторения каждой буквы и выведите на
//экран результат в порядке убывания относительной частоты
//повторения.
        try {
            new CountLetters(
                    "src\\oopHomeWork9\\letters\\CountLetters.java", true)
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
