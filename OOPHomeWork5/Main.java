package OOPHomeWork5;


import java.io.File;
import java.io.IOException;

import static OOPHomeWork5.Human.Sex.*;

public class Main {
    public static void main(String[] args) {
//        1. Напишите программу, которая скопирует файлы с заранее
//        определенным расширением(например, только doc) из
//        каталога источника в каталог приемник.
        String ext = "txt";
        String dirFrom = "c:\\Users\\user\\Downloads\\";
        String dirTo = "c:\\Users\\user\\IdeaProjects\\prog.kiev.ua\\src\\";
        try {
            DirToDir.copy(ext, dirFrom, dirTo);
        } catch (IOException e) {
            System.out.println("Ooops...");
            e.printStackTrace();
        }

        //2. Напишите программу, которая примет на вход два
        //текстовых файла, а вернет один. Содержимым этого файла
        //должны быть слова, которые одновременно есть и в первом и
        //во втором файле.
        File f1 = new File("c:\\Program Files\\BatteryMon\\ReadMe.txt");
        File f2 = new File("c:\\Program Files\\Git\\usr\\share\\gnupg\\help.txt");
        try {
            TwoFilesIntoOneFile.copySameWords(f1, f2);
        } catch (IOException e) {
            System.out.println("Ooops..");
            e.printStackTrace();
        }

//        3. Усовершенствуйте класс, описывающий группу студентов,
//           добавив возможность сохранения группы в файл.
        Group group = new Group("OOP");
        try{
            group.addStudent(new Student("John", "Flory", 35, MALE, "007"));
            group.addStudent(new Student("Penny", "Wise", 200, OTHER, "077"));
            group.addStudent(new Student("Elizabeth", "Lackersteen", 22, FEMALE, "066"));
            group.addStudent(new Student("U Po", "Kyin", 56, MALE, "345"));
            group.addStudent(new Student("Ma Hla", "May", 30, FEMALE, "333"));
            group.addStudent(new Student("Ko", "S'la", 16, MALE, "016"));
            group.addStudent(new Student("Dr", "Veraswami", 40, MALE, "099"));
            group.addStudent(new Student("Mr", "Macgregor", 17, MALE, "056"));
            group.addStudent(new Student("Francis", "Samuel", 16, MALE, "016"));
        } catch (TooManyStudentsException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }

        System.out.println(group);
        System.out.println("Saving to file...");
        File f = new File("Group " + group.getName() + ".txt");
        try {
            group.saveToFile(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4. Реализовать обратный процесс. Т.е. считать данные о
        //группе из файла, и на их основе создать объект типа группа
        System.out.println("Load group from file:");
        try {
            System.out.println(Group.loadFromFile(f));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
