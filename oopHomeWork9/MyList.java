package oopHomeWork9;

import java.util.LinkedList;
import java.util.List;

public class MyList {
    public static void main(String[] args) {
//1) Написать метод, который создаст список, положит в него 10
//элементов, затем удалит первые два и последний, а затем выведет
//результат на экран.
        doList();
    }

    private static void doList() {
        List<Integer> l = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            l.add(i);
        }
        l.remove(0);
        l.remove(0);
        l.remove(l.size() - 1);
        System.out.println(l);
    }
}
