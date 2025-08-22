public class Display {
  public static void main(String[] args) {
    Circle c = new Circle("red", 5.0);
    c.printColorUsage();
    System.out.println();

    Point rectP1 = new Point(0, 0);
    Point rectP2 = new Point(4, 3);
    Rectangle r = new Rectangle("blue", rectP1, rectP2);
    r.printColorUsage();
    System.out.println();

    Point triP1 = new Point(0, 0);
    Point triP2 = new Point(4, 0);
    Point triP3 = new Point(2, 3);
    Triangle t = new Triangle("green", triP1, triP2, triP3);
    t.printColorUsage();
    System.out.println();
  }
}
