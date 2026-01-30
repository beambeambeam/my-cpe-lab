public class Circle extends Shape {
  private double r;

  public Circle(String color, double r) {
    super(Color.valueOf(color.toUpperCase()));
    initRadius(r);
  }

  public Circle(Color color, double r) {
    super(color);
    initRadius(r);
  }

  private void initRadius(double r) {
    if (r <= 0) {
      throw new IllegalArgumentException("radius must be positive");
    }
    this.r = r;
  }

  @Override
  public double area() {
    return Math.PI * r * r;
  }

  @Override
  public double perimeter() {
    return 2 * Math.PI * r;
  }
}
