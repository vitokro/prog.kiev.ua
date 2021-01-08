package oopHomeWork8.stack;

import oopHomeWork8.serialization.Human;

public class Main {
    public static void main(String[] args) {
//2) Создайте класс-контейнер типа стек (класс в который можно добавлять и
//удалять объекты других классов, только в вершину стека), в который
//можно сохранять объекты произвольного типа. Должен быть метод
//добавления элемента в стек, получение с удалением элемента из стека, и
//просто получение элемента из вершины из стека. Должна быть
//реализована работа с «черным списком» классов (смотри ниже). Если
//объект который добавляется в стек принадлежит классу из «черного
//списка», то добавление такого объекта запрещено.
//3) Для описанного выше стека создайте класс «Черный список», в котором
//будут описаны классы объектов которые нельзя добавлять в стек.
//Должна быть возможность добавления классов в черный список, проверка
//объекта на то, что класс, к которому он принадлежит, принадлежит или не
//принадлежит к классам в черном списке.
        BlackList bl = new BlackList();
        bl.add(bl.getClass());
        bl.add(bl);
        bl.add(Stack.class);
        bl.add("");
        bl.add(' ');
        bl.add(44);
        bl.add(3242423);
        bl.add(0.0);
        bl.add(324223243242424223L);
        bl.add(new Object());
        bl.add(Class.class);
        bl.add(Class.class);
        System.out.println(bl);

        Stack stack = new BlackStack(bl);
        stack.push(new Human("name", "surname", 76, Human.Sex.MALE));
        stack.push((byte) 125);
        System.out.println(stack);
        stack.push(stack);
        stack.push(0);
        System.out.println(stack);




    }
}
