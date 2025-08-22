public abstract class Shape {
  public enum Color {
    RED, GREEN, BLUE, BLACK
  }

  private Color color;
  private Color borderColor;
  private double borderThickness;

  public Shape() {
    // default
    this.color = null;
    this.borderColor = Color.BLACK;
    this.borderThickness = 1.0;
  }

  public Shape(Color color) {
    this.color = color;
    this.borderColor = Color.BLACK;
    this.borderThickness = 1.0;
  }

  public void shape(Color color2) {
    this.color = color2;
    this.borderColor = Color.BLACK;
    this.borderThickness = 1.0;
  }

  public Color getColor() {
    return color;
  }

  public Color getBorderColor() {
    return borderColor;
  }

  public double getBorderThickness() {
    return borderThickness;
  }

  public abstract double area();

  public abstract double perimeter();

  public static final void printColorUsage(Shape[] shapes) {
    int red = 0, green = 0, blue = 0, black = 0;
    if (shapes != null) {
      for (Shape s : shapes) {
        if (s == null)
          continue;
        Color c = s.getColor();
        if (c == null)
          continue;
        switch (c) {
          case RED:
            red++;
            break;
          case GREEN:
            green++;
            break;
          case BLUE:
            blue++;
            break;
          case BLACK:
            black++;
            break;
          default:
            break;
        }
      }
    }

    System.out.println("red: " + red);
    System.out.println("green: " + green);
    System.out.println("blue: " + blue);
    System.out.println("black: " + black);
  }
}
