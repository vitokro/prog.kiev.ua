package OOPHomeWork2;

public class Triangle extends Shape {
    private Point point1;
    private Point point2;
    private Point point3;
    private double side1;
    private double side2;
    private double side3;

    public Triangle(Point point1, Point point2, Point point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        side1 = Point.getDistance(point1, point2);
        side2 = Point.getDistance(point1, point3);
        side3 = Point.getDistance(point3, point2);
    }


    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public Point getPoint3() {
        return point3;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public void setPoint3(Point point3) {
        this.point3 = point3;
    }

    @Override
    public double getPerimetr() {
        return side1 + side2 + side3;
    }

    @Override
    public double getArea() {
        double p = (side1 + side2 + side3) / 2;
        return Math.sqrt(p * (p - side1) * (p - side1) * (p - side1));
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "point1=" + point1 +
                ", point2=" + point2 +
                ", point3=" + point3 +
                '}';
    }
}
