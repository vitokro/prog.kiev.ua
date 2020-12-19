package OOPHomeWork2;

public class Main {

    public static void main(String[] args) {
        Triangle tr = new Triangle(new Point(1, 1), new Point(4,1), new Point(1, 5));
        System.out.println(tr);
        System.out.println("area = " + tr.getArea());
        System.out.println("P = " + tr.getPerimetr());
        System.out.println();

        Circle c = new Circle(new Point(5, 5), new Point(2,5));
        System.out.println(c);
        System.out.println("area = " +c.getArea());
        System.out.println("P = " + c.getPerimetr());
        System.out.println();

        Square s = new Square(new Point(1, 1), new Point(1, 10));
        System.out.println(s);
        System.out.println("area = " + s.getArea());
        System.out.println("P = " + s.getPerimetr());
        System.out.println();

        Board b = new Board();
        b.put(tr, 0);
        b.put(s, 1);
        b.put(c, 3);
        b.put(c, 6);
        System.out.println("totalArea = "+ b.getTotalArea());
    }
}
