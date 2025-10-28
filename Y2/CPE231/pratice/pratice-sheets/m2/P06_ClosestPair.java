import java.util.*;

public class P06_ClosestPair {

  static class Point {
    double x, y;

    Point(double x, double y) {
      this.x = x;
      this.y = y;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      points[i] = new Point(sc.nextDouble(), sc.nextDouble());
    }
    sc.close();

    Arrays.sort(points, Comparator.comparingDouble(p -> p.x));
    double minDist = closestPair(points, 0, n - 1);
    System.out.printf("%.3f\n", minDist);
  }

  private static double closestPair(Point[] pts, int left, int right) {
    if (right - left <= 3) {
      return bruteForce(pts, left, right);
    }

    int mid = (left + right) / 2;
    double midX = pts[mid].x;

    double dLeft = closestPair(pts, left, mid);
    double dRight = closestPair(pts, mid + 1, right);
    double d = Math.min(dLeft, dRight);

    // Build strip
    List<Point> strip = new ArrayList<>();
    for (int i = left; i <= right; i++) {
      if (Math.abs(pts[i].x - midX) < d) {
        strip.add(pts[i]);
      }
    }

    strip.sort(Comparator.comparingDouble(p -> p.y));

    // Check strip
    for (int i = 0; i < strip.size(); i++) {
      for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < d; j++) {
        d = Math.min(d, dist(strip.get(i), strip.get(j)));
      }
    }

    return d;
  }

  private static double bruteForce(Point[] pts, int left, int right) {
    double min = Double.MAX_VALUE;
    for (int i = left; i <= right; i++) {
      for (int j = i + 1; j <= right; j++) {
        min = Math.min(min, dist(pts[i], pts[j]));
      }
    }
    return min;
  }

  private static double dist(Point a, Point b) {
    double dx = a.x - b.x;
    double dy = a.y - b.y;
    return Math.sqrt(dx * dx + dy * dy);
  }
}
