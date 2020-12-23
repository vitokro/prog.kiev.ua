package OOPHomeWork3;

import static OOPHomeWork3.Human.Sex.*;

public class Main {
    public static void main(String[] args) throws NoStudentsInGroupException {
        System.out.println(new Human("name", "surname", 76, OTHER));
        System.out.println();

        Group group = new Group("OOP");

        // try to delete from empty group
        try {
            group.delStudent("333");
        } catch (NoStudentsInGroupException e) {
            System.out.println(e.getMessage());
        }
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
            group.addStudent(new Student("George", "Orwell", 46, MALE, "984"));
//            group.addStudent(new Student("George", "Orwell2", 46, MALE, "985"));

        } catch (TooManyStudentsException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
        System.out.println(group);

        group.delStudent("545");
//        group.delStudent("035");
//        group.delStudent("077");
        System.out.println();
        System.out.println(group);

//        // find by bookid
//        System.out.println(group.findStudent("Lackersteen"));
//        System.out.println(group.findStudent("May"));
//        System.out.println(group.findStudent("Orwell"));
//        System.out.println();
//
//        // try to delete student
//        try {
//            group.delStudent("333");
//        } catch (NoStudentsInGroupException e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println(group);
//        System.out.println("try to add Orwell");
//        System.out.println();
//        try {
//            group.addStudent(new Student("George", "Orwell", 46, MALE, "984"));
//        } catch (TooManyStudentsException e) {
//            System.out.println(e.getMessage());
//            System.out.println();
//        }
//
//        System.out.println(group);

    }
}
