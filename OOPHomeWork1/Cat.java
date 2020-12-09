package OOPHomeWork1;

import java.util.Objects;
import java.util.Random;

public class Cat {
    private String name;
    private String sex;
    private String color;
    private String breed;
    private int age;
    private Cat parent1;
    private Cat parent2;

    public Cat(String name, String sex, String color, String breed, int age) {
        this.name = name;
        this.sex = sex;
        this.color = color;
        this.breed = breed;
        this.age = age;
    }

    public Cat() {
    }

    public void meow(){
        System.out.println(name + " says Meow!");
    }

    public Cat[] giveBirth(Cat parent2){
        if (Objects.equals("male", sex) || parent2.sex.equals("female"))
            return null;
        else {
            Random ran = new Random();
            int numberOfKittens = ran.nextInt(7) + 1;
            Cat[] kittens = new Cat[numberOfKittens];
            for (int i = 0; i < numberOfKittens; i++) {
                String sex = ran.nextInt(3) % 2 == 0 ? "male" : "female";
                String breed = ran.nextInt(3) % 2 == 0 ? this.breed : parent2.breed;
                String color;
                switch (ran.nextInt(5)){
                    case 0: color = "white"; break;
                    case 1: color = "black"; break;
                    case 2: color = "red"; break;
                    case 3: color = "black-white"; break;
                    default: color = "white-black";
                }
                kittens[i] = new Cat("kitten" + (i + 1), sex, color, breed, 0);
                kittens[i].setParent1(this);
                kittens[i].setParent2(parent2);
                kittens[i].meow();
            }
            return kittens;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cat getParent1() {
        return parent1;
    }

    public void setParent1(Cat parent1) {
        this.parent1 = parent1;
    }

    public Cat getParent2() {
        return parent2;
    }

    public void setParent2(Cat parent2) {
        this.parent2 = parent2;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

       public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", color='" + color + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", parent1=" + parent1.name +
                ", parent2=" + parent2.name +
                '}';
    }

}
