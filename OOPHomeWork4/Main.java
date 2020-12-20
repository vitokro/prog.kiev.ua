package OOPHomeWork4;

import static OOPHomeWork4.Human.Sex.*;

public class Main {
    public static void main(String[] args) {
        Group group = new Group("OOP");

        // fill the group
        try{
            group.addStudentFromInput();
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

        System.out.println();
        for(var s: group.getRecrut())
            System.out.println(s);

    }
}
