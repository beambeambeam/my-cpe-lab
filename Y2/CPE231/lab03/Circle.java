public class Circle extends Shape {
  private double r; // radius

  public Circle(String color, double r) {
    super(Color.valueOf(color.toUpperCase()));
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
