package oopHomeWork8.serialization;

import java.io.File;
import java.io.IOException;

import static oopHomeWork8.serialization.Human.Sex.*;

public class Main {
    public static void main(String[] args) {
//1) Используя стандартный методы сериализации создайте мини-базу
//данных для работы с группами студентов (возможность записи и чтения
//базы из файла по запросу пользователя).
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

        try {
            group.delStudent("007");
            group.delStudent("077");
        } catch (NoStudentsInGroupException e) {
            e.printStackTrace();
        }
        System.out.println("Delete some students:");
        System.out.println(group);

        System.out.println("After loading from file:");
        try {
            System.out.println(Group.loadFromFile(f));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
