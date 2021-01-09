package oopHomeWork9;

import java.util.ArrayDeque;
import java.util.Deque;

public class DoubleCola {

    public static void main(String[] args) {
//4) Шелдон, Леонард, Воловиц, Кутрапалли и Пенни стоят в очереди
//за «двойной колой». Как только человек выпьет такой колы, он
//раздваивается и оба становятся в конец очереди, чтобы выпить еще
//стаканчик. Напишите программу, которая выведет на экран
//состояние очереди в зависимости от того, сколько стаканов колы
//выдал аппарат с чудесным напитком. Например, если было выдано
//только два стакана, то очередь выглядит как:
//[Volovitc, Kutrapalli, Penny, Sheldon, Sheldon, Leonard, Leonard]
        doubleCola(2);
    }

    private static void doubleCola(int n) {
        Deque<String> que = new ArrayDeque<>();
        que.add("Sheldon");
        que.add("Leonard");
        que.add("Volovitc");
        que.add("Kutrapalli");
        que.add("Penny");
        for (int i = 0; i < n; i++) {
            String tmp = que.poll();
            que.add(tmp);
            que.add(tmp);
        }
        System.out.println(que);
    }
}
