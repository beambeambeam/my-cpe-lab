public abstract class Shape {
  public enum Color {
    RED, GREEN, BLUE, BLACK
  }

  private Color color;
  private Color borderColor;
  private double borderThickness;

  public Shape(Color color) {
    if (color == Color.BLACK) {
      throw new IllegalArgumentException("BLACK color is not allowed.");
    }
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

  public final void printColorUsage() {
    int red = 0, green = 0, blue = 0, black = 0;
    Color c = this.getColor();
    if (c != null) {
      switch (c) {
        case RED:
          red += area();
          break;
        case GREEN:
          green += area();
          break;
        case BLUE:
          blue += area();
          break;
        default:
          break;
      }
    }

    black = (int) ((int) getBorderThickness() * perimeter());

    System.out.println("red: " + red);
    System.out.println("green: " + green);
    System.out.println("blue: " + blue);
    System.out.println("black: " + black);
  }
}
