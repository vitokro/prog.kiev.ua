package OOPHomeWork3;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Group {
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
    @Override
    public String toString() {
        Arrays.sort(students, Comparator.nullsLast(Comparator.comparing(Human::getSurname)));
        StringBuilder s = new StringBuilder();
        s.append(String.format("Group %s has %d students:\n", name, count));
        for (int i = 0; i < count; i++) {
            s.append(students[i]);
            s.append('\n');
        }
        return s.toString();
    }
}
