package oopHomeWork7;

import oopHomeWork7.FileCopy.MultiThreadCopyFile;
import oopHomeWork7.findFile.MultiThreadFindFile;
import oopHomeWork7.port.Dock;
import oopHomeWork7.port.Port;
import oopHomeWork7.port.Ship;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        1) Существуют три корабля. На каждом из них 10 ящиков груза.
//        Они одновременно прибыли в порт, в котором только два
//        дока. Скорость разгрузки - 1 ящик в 0.5 сек. Напишите
//        программу, которая, управляя кораблями, позволит им
//        правильно разгрузить груз.
        Ship[] ships = new Ship[]{
                new Ship("Poltava", 10),
                new Ship("Pobeda", 10),
                new Ship("Titanic", 10),
//                new Ship("Potiomkin", 15),
//                new Ship("Kiev", 7),
//                new Ship("Sevastopol", 13),
//                new Ship("Yalta", 6),
        };
        Dock[] docks = new Dock[]{
                new Dock(1),
                new Dock(2),
//                new Dock(3),
//                new Dock(4),
        };
        new Port(ships, docks).unloadShips();
//

//        2) Реализуйте программу многопоточного копирования файла
//        блоками с выводом прогресса на экран.
            new MultiThreadCopyFile(
                    "c:\\Users\\user\\Downloads\\Telegram Desktop\\Java OOP. Лекция 5.mp4",
                    "c:\\Users\\user\\Downloads\\")
                    .copy();

//        3) Реализуйте процесс многопоточного поиска файла в
//        файловой системе. Т.е. вы вводите название файла и в какой
//        части файловой системы его искать. Программа должна
//        вывести на экран все адреса файлов с таким названием.
        try {
            new MultiThreadFindFile(
                    new File("c:\\Program Files"),
                    new File("readme.txt"),
                    6)
                    .find();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}



