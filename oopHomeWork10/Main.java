package oopHomeWork10;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Main {
    public static void main(String[] args) {
// 1. Написать программу - переводчик, которая будет переводить
//текст в файле English.in, написанный на английском языке, на
//украинский язык, согласно заранее составленному словарю.
//Результат сохранить в файл Ukrainian.out.
//2. Сделать ф-ю ручного наполнения словаря и возможность его
//сохранения на диск.
        Dictionary d = new Dictionary();
        d.inputDict();
        d.translate();

//3. Решить задачу подсчета повторяющихся элементов в массиве с
//помощью HashMap.
        statArray();
    }

    private static void statArray() {
        String[] arr = new String[] {"Inna", "Ira", "Andrey", "Vova", "Sergey", "Anton", "Dmitriy",
                "Alexandr", "Olga", "Anna"};
        List<String> names = Stream.generate(() -> arr[(int) (Math.random() * arr.length)]).limit(20).collect(toList());
        System.out.println(names);
        Map<String, Long> stat = names.stream().collect(groupingBy(n -> n, counting()));
        System.out.println(stat);
    }
}
