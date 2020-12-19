package OOPHomeWork3;

public class Human {
    public enum Sex{
        MALE,
        FEMALE,
        OTHER
    }

    private String name;
    private String surname;
    private int age;
    private Sex sex;

    public Human() {
    }

    public Human(String name, String surname, int age, Sex sex) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return String.format("Human: %s %s, %d years old, %s", name, surname, age, sex);
    }
}
