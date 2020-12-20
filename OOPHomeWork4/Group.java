package OOPHomeWork4;

import OOPHomeWork4.Human.Sex;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

import static OOPHomeWork4.Human.Sex.MALE;

public class Group implements Voencom{
    private String name;
    private Student[] students = new Student[10];
    private int count = 0;

    public Group(String name) {
        this.name = name;
    }

    public Group() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addStudent(Student student) throws TooManyStudentsException {
        if (student == null)
            return;
        if (count == 10)
            throw new TooManyStudentsException();
        this.students[count++] = student;
    }

    public void addStudentFromInput() throws TooManyStudentsException {
        if (count == 10)
            throw new TooManyStudentsException();
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter student's name:");
            String name = sc.nextLine();
            System.out.println("Enter student's surname:");
            String surname = sc.nextLine();
            int age = 0;
            System.out.println("Enter student's age:");
            while (sc.hasNext()) {
                if (sc.hasNextInt()) {
                    age = sc.nextInt();
                    sc.nextLine();
                    break;
                }
                System.out.println("Illegal age! Try again:");
                sc.nextLine();
            }
            System.out.println("Enter student's sex:");
            Sex sex = null;
            do {
                try {
                    sex = Sex.valueOf(sc.nextLine().toUpperCase());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Illegal sex! Try again:");
                }
            } while (true);
            System.out.println("Enter student's ID:");
            String bookId = sc.nextLine();
            this.students[count++] = new Student(name, surname, age, sex, bookId);
        }
    }

    public void delStudent(String bookId) throws NoStudentsInGroupException {
        for (int i = 0; i < count; i++) {
            if (students[i].getBookId().equals(bookId)){
                students[i] = null;
                count--;
                return;
            }
        }
        throw new NoStudentsInGroupException();
    }

    public Student findStudent(String surname){
        if (surname == null)
            return null;
        for (int i = 0; i < count; i++) {
            if (students[i].getSurname().equals(surname))
                return students[i];
        }
        return null;
    }

    public void sortBySurname(){
        Arrays.sort(students, Comparator.nullsLast(Comparator.comparing(Human::getSurname)));
    }

    public void sortByName(){
        Arrays.sort(students, Comparator.nullsLast(Comparator.comparing(Human::getName)));
    }

    @Override
    public String toString() {
        sortBySurname();
        StringBuilder s = new StringBuilder();
        s.append(String.format("Group %s has %d students:\n", name, count));
        for (int i = 0; i < count; i++) {
            s.append(students[i]);
            s.append('\n');
        }
        return s.toString();
    }


    @Override
    public Student[] getRecrut() {
        return Arrays.stream(students).filter((s)->(s.getSex() == MALE && s.getAge() > 18)).toArray(Student[]::new);
    }
}
