package OOPHomeWork1;

public class Main {
    public static void main(String[] args) {
        // -------------CAT----------------
        Cat kiska = new Cat("Kiska","female", "red", "unknown", 3);
        Cat murzikVasiliovich = new Cat("murzik", "male", "black", "siam", 5);

        murzikVasiliovich.meow();
        kiska.meow();

        for(Cat kitten: kiska.giveBirth(murzikVasiliovich)){
            System.out.println(kitten);
        }
        // -------------TRIANGLE----------------
        Triangle tr1 = new Triangle(4, 3,5);
        System.out.println(tr1);
        System.out.println("Area = " + tr1.getArea());


        Triangle tr2 = new Triangle(44, 33,55);
        System.out.println(tr2);
        System.out.println("Area = " + tr2.getArea());

        Triangle tr3 = new Triangle(1.325, 2.34534,1.38);
        System.out.println(tr3);
        System.out.println("Area = " + tr3.getArea());


        // -------------PHONE----------------
        Network networkAT = new Network("At&t");
        Phone phone1 = new Phone("0995555555", "iChina");
        Phone phone2 = new Phone("0995555511", "Nokia");
        Phone phone3 = new Phone("0995555522", "Sansum");
        phone1.register(networkAT);
        phone2.register(networkAT);

        phone1.Call(phone2.getNumber());
        phone1.Call(phone3.getNumber());
        phone1.Call(phone1.getNumber());

        phone2.Call(phone1.getNumber());
        phone2.Call(phone3.getNumber());

        phone3.Call(phone2.getNumber());
        phone3.Call(phone1.getNumber());

//        Домашнее задание:
//        1) Описать класс «Cat» (в качестве образца можно взять домашнего питомца).
//        Наделить его свойствами и методами. Создать несколько экземпляров объектов
//        этого класса. Использовать эти объекты.
//        2) Описать класс «Triangle». В качестве свойств возьмите длины сторон
//        треугольника. Реализуйте метод, который будет возвращать площадь этого
//        треугольника. Создайте несколько объектов этого класса и протестируйте их.
//        3) Описать класс «Vector3d» (т. е., он должен описывать вектор в трехмерной,
//                декартовой системе координат). В качестве свойств этого класса возьмите
//        координаты вектора. Для этого класса реализовать методы сложения, скалярного и
//        векторного произведения векторов. Создайте несколько объектов этого класса и
//        протестируйте их.
//        4) Опишите класс Phone (одним из свойств должен быть его номер). Также опишите
//        класс Network (сеть мобильного оператора). Телефон должен иметь метод
//        регистрации в сети мобильного оператора. Также у телефона должен быть метод call
//                (номер другого телефона), который переберет все телефоны, зарегистрированные в
//        сети. Если такой номер будет найден, то осуществить вызов, если нет - вывести
//        сообщение об ошибочности набранного номера.

    }
}
