public class Rectangle extends Shape {
  private Point p1; // starting point (top-left corner)
  private Point p2; // ending point (bottom-right corner)

  public Rectangle(String color, Point p1, Point p2) {
    super(Color.valueOf(color.toUpperCase()));
    initPoints(p1, p2);
  }

  public Rectangle(Color color, Point p1, Point p2) {
    super(color);
    initPoints(p1, p2);
  }

  private void initPoints(Point a, Point b) {
    if (a == null || b == null) {
      throw new NullPointerException("points must not be null");
    }
    this.p1 = a;
    this.p2 = b;
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
