package OOPHomeWork2;

public class Circle extends Shape {
    private Point pointCenter;
    private Point pointCircle;
    private double radius;

    public Circle(Point pointCenter, Point pointCircle) {
        this.pointCenter = pointCenter;
        this.pointCircle = pointCircle;
        radius = Math.abs(Point.getDistance(pointCenter, pointCircle));
    }

    public Circle() {
    }

    public Point getPointCenter() {
        return pointCenter;
    }

    public void setPointCenter(Point pointCenter) {
        this.pointCenter = pointCenter;
    }

    public Point getPointCircle() {
        return pointCircle;
    }

    public void setPointCircle(Point pointCircle) {
        this.pointCircle = pointCircle;
    }

    @Override
    public double getPerimetr() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "pointCenter=" + pointCenter +
                ", pointCircle=" + pointCircle +
                '}';
    }
}
