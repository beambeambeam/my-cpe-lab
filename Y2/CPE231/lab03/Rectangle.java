public class Rectangle extends Shape {
  private Point p1; // starting point (top-left corner)
  private Point p2; // ending point (bottom-right corner)

  public Rectangle(String color, Point p1, Point p2) {
    super(Color.valueOf(color.toUpperCase()));
    this.p1 = p1;
    this.p2 = p2;
  }

  public Point getP1() {
    return p1;
  }

  public Point getP2() {
    return p2;
  }

  private double getWidth() {
    return Math.abs(p2.getX() - p1.getX());
  }

  private double getHeight() {
    return Math.abs(p2.getY() - p1.getY());
  }

  @Override
  public double area() {
    return getWidth() * getHeight();
  }

  @Override
  public double perimeter() {
    return 2 * (getWidth() + getHeight());
  }
}
