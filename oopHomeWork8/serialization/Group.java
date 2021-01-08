package oopHomeWork8.serialization;

import oopHomeWork8.serialization.Human.Sex;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import static oopHomeWork8.serialization.Human.Sex.MALE;

public class Group implements Voencom, Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Student[] students = new Student[10];
    private int count = 0;

    public Group(String name) {
        this.name = name;
    }

    public Group() {
    }

    public static Group loadFromFile(File f) throws IOException, ClassNotFoundException {
        try (ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(f))){
            return (Group)OIS.readObject();
        }
    }

    public void saveToFile(File f) throws IOException {
        try(ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(f))){
            OOS.writeObject(this);
        }
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
                for (int j = i; j < count - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[--count] = null;
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
