public class Triangle extends Shape {
  private Point p1;
  private Point p2;
  private Point p3;

  public Triangle(String color, Point p1, Point p2, Point p3) {
    super(Color.valueOf(color.toUpperCase()));
    this.p1 = p1;
    this.p2 = p2;
    this.p3 = p3;
  }

  public Point getP1() {
    return p1;
  }

  public Point getP2() {
    return p2;
  }

  public Point getP3() {
    return p3;
  }

  @Override
  public double area() {
    double a = p1.distanceTo(p2);
    double b = p2.distanceTo(p3);
    double c = p3.distanceTo(p1);
    double s = (a + b + c) / 2.0;
    return Math.sqrt(s * (s - a) * (s - b) * (s - c));
  }

  @Override
  public double perimeter() {
    double side1 = p1.distanceTo(p2);
    double side2 = p2.distanceTo(p3);
    double side3 = p3.distanceTo(p1);

    return side1 + side2 + side3;
  }
}
