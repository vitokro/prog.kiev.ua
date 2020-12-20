package OOPHomeWork4;

public class Student extends Human {

    private String bookId;

    public Student(String bookId) {
    }

    public Student(String name, String surname, int age, Sex sex, String bookId) {
        super(name, surname, age, sex);
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return String.format("%s Student %s %s, %s", bookId, getName(), getSurname(), getSex());
    }
}
