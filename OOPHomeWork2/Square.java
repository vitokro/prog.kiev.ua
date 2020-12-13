package OOPHomeWork2;

public class Square extends Shape {
    private Point point1;
    private Point point2;
    private double side;

    public Square(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
        side = Point.getDistance(point1, point2);
    }

    public Square() {
    }

    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    @Override
    public double getPerimetr() {
        return 4 * side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public String toString() {
        return "Square{" +
                "point1=" + point1 +
                ", point2=" + point2 +
                '}';
    }
}
