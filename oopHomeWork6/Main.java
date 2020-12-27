package oopHomeWork6;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        1) Создайте сто потоков, которые будут вычислять факториал
//           числа, равного номеру этого потока, и выводить результат на
//           экран.
        {
            for (int i = 1; i <= 100; i++) {
                new Thread(new OneHundredThreads(i)).start();
            }
        }


//        2) Написать код для многопоточного подсчета суммы элементов
//           массива целых чисел. Сравнить скорость подсчета с простым
//           алгоритмом.
            task2();


//       3) Напишите многопоточный вариант сортировки массива
//          методом Шелла.
        {
            Random r = new Random();
            int size = 19999;
            int[] a = new int[size];
            for (int i = 0; i < size; i++) {
                a[i] = r.nextInt(100000);
            }
            new MultiThreadShellSort(a, 30).sort();
        }


//        4) Реализуйте многопоточное копирование каталога,
//        содержащего несколько файлов
        {
            CopyDir.copyM("c:\\Users\\user\\Documents\\books\\1\\", "c:\\Users\\user\\Documents\\Zoom\\");
        }


//        5) Реализуйте программу, которая с периодичностью 1 сек,
//                будет проверять состояние заданного каталога. Если в этом
//        каталоге появится новый файл или исчезнет существующий,
//                то выведется сообщение об этом событии. Программа
//        должна работать в параллельном потоке выполнения.
        {
            ScheduledExecutorService exs = Executors.newSingleThreadScheduledExecutor();
            exs.scheduleAtFixedRate(new FileListener("c:\\\\Users\\\\user\\\\Documents\\\\books\\\\1\\\\"), 1, 1,
                    TimeUnit.SECONDS);

            System.out.println("To exit press enter");
            if (new Scanner(System.in).hasNextLine())
                exs.shutdown();
        }
    }


    private static void task2() {
        Random r = new Random();
        // init array
        int size = 222222222;
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = r.nextInt(10000);
        }
        // static method count
        long startTime = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < size; i++) {
            sum += a[i];
        }
        long endTime = System.currentTimeMillis();

        System.out.println((endTime - startTime) + "ms static method time");
        System.out.println("sum = " + sum);

        // multithread method
        int threadsNumber = 4;
        startTime = System.currentTimeMillis();
        var multiThreadCountArraySum = new MultiThreadCountArraySum(threadsNumber, a);
        multiThreadCountArraySum.count();
        endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + "ms multiThread method time");
        System.out.println("sum = " + multiThreadCountArraySum.getSum());

        // Або я щось не так зробив, але майже ніякого виграшу немає з багатопоточністю:
        // output:
        //107ms static method time
        //sum = 999964804
        //Thread[Thread-2,5,main] sum = 250024162
        //Thread[Thread-0,5,main] sum = 249997318
        //Thread[Thread-3,5,main] sum = 249971710
        //Thread[Thread-1,5,main] sum = 249971614
        //103ms multiThread method time
        //sum = 999964804


    }
}
