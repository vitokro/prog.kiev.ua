package oopHomeWork9.group;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static oopHomeWork9.group.Human.Sex.MALE;


public class Group implements Voencom, Serializable {
    private static final long serialVersionUID = 2L;
    private String name;
    private List<Student> students = new ArrayList<>();

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
        if (students.size() == 10)
            throw new TooManyStudentsException();
        students.add(student);
    }

    public void addStudentFromInput() throws TooManyStudentsException {
        if (students.size() == 10)
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
            Human.Sex sex = null;
            do {
                try {
                    sex = Human.Sex.valueOf(sc.nextLine().toUpperCase());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Illegal sex! Try again:");
                }
            } while (true);
            System.out.println("Enter student's ID:");
            String bookId = sc.nextLine();
            students.add(new Student(name, surname, age, sex, bookId));
        }
    }

    public void delStudent(String bookId) throws NoStudentsInGroupException {
        Iterator<Student> itr = students.iterator();
        while(itr.hasNext()){
            if (itr.next().getBookId() == bookId) {
                itr.remove();
                return;
            }
        }
        throw new NoStudentsInGroupException();
    }

    public Student findStudent(String surname){
        if (surname == null)
            return null;
        Iterator<Student> itr = students.iterator();
        while(itr.hasNext()){
            Student st = itr.next();
            if (st.getSurname() == surname) {
                return st;
            }
        }
        return null;
    }

    public void sortBySurname(){
        Collections.sort(students, Comparator.nullsLast(Comparator.comparing(Human::getSurname)));
    }

    public void sortByName(){
        Collections.sort(students, Comparator.nullsLast(Comparator.comparing(Human::getName)));
    }

    public List<Student> getStudentsByFirstLetter(String s){
        return students.stream()
                       .filter(n -> n.getSurname().startsWith(s.toUpperCase()))
                       .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        sortBySurname();
        StringBuilder s = new StringBuilder();
        s.append(String.format("Group %s has %d students:\n", name, students.size()));
        for (int i = 0; i < students.size(); i++) {
            s.append(students.get(i));
            s.append('\n');
        }
        return s.toString();
    }


    @Override
    public List<Student> getRecrut() {
        return students.stream().filter((s)->(s.getSex() == MALE && s.getAge() > 18)).collect(Collectors.toList());
    }
}
