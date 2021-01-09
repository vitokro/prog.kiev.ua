package oopHomeWork9.group;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import static oopHomeWork9.group.Human.Sex.*;

public class Main {
    public static void main(String[] args) {
//        2) Модифицируйте класс «Группа» для более удобных методов
//        работы с динамическими массивами
        Group group = new Group("OOP");
        fillGroup(group);
        delSomeStudents(group);
        findById(group);
        addOrwell(group);
        File f = savingToFile(group);
        delSomeStudents2(group);
        loadGroup(f);
        recruts(group);
    }

    private static void recruts(Group group) {
        System.out.println("Recruts:");
        System.out.println(group.getRecrut());
    }

    private static void loadGroup(File f) {
        System.out.println("After loading from file:");
        try {
            System.out.println(Group.loadFromFile(f));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void delSomeStudents2(Group group) {
        try {
            group.delStudent("007");
            group.delStudent("077");
        } catch (NoStudentsInGroupException e) {
            e.printStackTrace();
        }
        System.out.println("Delete some students:");
        System.out.println(group);
    }

    @NotNull
    private static File savingToFile(Group group) {
        System.out.println("Saving to file...");
        File f = new File("Group " + group.getName() + ".txt");
        try {
            group.saveToFile(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }

    private static void addOrwell(Group group) {
        System.out.println("try to add Orwell");
        System.out.println();
        try {
            group.addStudent(new Student("George", "Orwell", 46, MALE, "984"));
        } catch (TooManyStudentsException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
        System.out.println(group);

    }

    private static void fillGroup(Group group) {
        // fill the group
        try{
            group.addStudent(new Student("John", "Flory", 35, MALE, "007"));
            group.addStudent(new Student("Penny", "Wise", 200, OTHER, "077"));
            group.addStudent(new Student("Elizabeth", "Lackersteen", 22, FEMALE, "066"));
            group.addStudent(new Student("U Po", "Kyin", 56, MALE, "345"));
            group.addStudent(new Student("Ma Hla", "May", 30, FEMALE, "333"));
            group.addStudent(new Student("Ko", "S'la", 16, MALE, "016"));
            group.addStudent(new Student("Dr", "Veraswami", 40, MALE, "099"));
            group.addStudent(new Student("Mr", "Macgregor", 17, MALE, "056"));
            group.addStudent(new Student("Francis", "Samuel", 16, MALE, "089"));
            group.addStudent(new Student("Mrs", "Lackersteen", 35, FEMALE, "035"));
//            group.addStudent(new Student("George", "Orwell", 46, MALE, "984"));
//            group.addStudent(new Student("George", "Orwell2", 46, MALE, "985"));

        } catch (TooManyStudentsException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
        System.out.println(group);
    }

    private static void delSomeStudents(Group group) {
        System.out.println("Delete some students:");
        try {
//            group.delStudent("545");
            group.delStudent("035");
            group.delStudent("077");
        } catch (NoStudentsInGroupException noStudentsInGroupException) {
            noStudentsInGroupException.printStackTrace();
        }
        System.out.println();
        System.out.println(group);
    }

    private static void findById(Group group) {
        // find by bookid
        System.out.println(group.findStudent("Lackersteen"));
        System.out.println(group.findStudent("May"));
        System.out.println(group.findStudent("Orwell"));
        System.out.println();
    }
}
