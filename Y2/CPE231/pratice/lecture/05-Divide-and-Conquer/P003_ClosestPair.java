public class P003_ClosestPair {

  private static double d(int x1, int y1, int x2, int y2) {
    int dx = x1 - x2;
    int dy = y1 - y2;
    return Math.sqrt(dx * dx + dy * dy);
  }

  private static double closest(int[] listX, int[] listY, int l, int r) {
    System.out.println(
        "closest(" + l + ", " + r + ") -> " + java.util.Arrays.toString(java.util.Arrays.copyOfRange(listX, l, r)) + " "
            + java.util.Arrays.toString(java.util.Arrays.copyOfRange(listY, l, r)));

    if (l >= r - 1) {
      System.out.println("  Base case: only one point, returning MAX_VALUE");
      return Integer.MAX_VALUE;
    }

    if (r - l == 2) {
      double dist = d(listX[l], listY[l], listX[l + 1], listY[l + 1]);
      System.out.println("  Base case: two points, dist=" + dist);
      return dist;
    }

    if (r - l == 3) {
      double d1 = d(listX[l], listY[l], listX[l + 1], listY[l + 1]);
      double d2 = d(listX[l], listY[l], listX[l + 2], listY[l + 2]);
      double d3 = d(listX[l + 1], listY[l + 1], listX[l + 2], listY[l + 2]);
      double minDist = Math.min(d1, Math.min(d2, d3));
      System.out.println("  Base case: three points, d1=" + d1 + ", d2=" + d2 + ", d3=" + d3 + ", min=" + minDist);
      return minDist;
    }

    int m = (l + r) / 2;
    System.out.println("  Recursing: left(" + l + ", " + m + ") right(" + m + ", " + r + ")");
    double leftMin = closest(listX, listY, l, m);
    double rightMin = closest(listX, listY, m, r);
    double min = Math.min(leftMin, rightMin);
    System.out.println("  Min from halves: " + min);

    // Count points in strip
    int stripCount = 0;
    for (int i = l; i < r; i++) {
      if (Math.abs(listX[i] - listX[m]) < min) {
        stripCount++;
      }
    }

    // Create strip array and populate it
    int[] strip = new int[stripCount];
    int stripIndex = 0;
    for (int i = l; i < r; i++) {
      if (Math.abs(listX[i] - listX[m]) < min) {
        strip[stripIndex++] = i;
      }
    }

    // Sort strip by y-coordinate
    for (int i = 0; i < stripCount - 1; i++) {
      for (int j = i + 1; j < stripCount; j++) {
        if (listY[strip[i]] > listY[strip[j]]) {
          int temp = strip[i];
          strip[i] = strip[j];
          strip[j] = temp;
        }
      }
    }

    for (int i = 0; i < stripCount; i++) {
      for (int j = i + 1; j < stripCount && (listY[strip[j]] - listY[strip[i]]) < min; j++) {
        double dVal = d(listX[strip[i]], listY[strip[i]], listX[strip[j]], listY[strip[j]]);
        if (dVal < min) {
          System.out.println("  Strip closer: " + dVal + " between " + strip[i] + " and " + strip[j]);
          min = dVal;
        }
      }
    }
    System.out.println("  Returning min distance " + min + " for range [" + l + ", " + r + ")");
    return min;
  }

  public static void main(String[] args) {
    java.util.Random rand = new java.util.Random();
    int n = 10;
    int[] listX = new int[n];
    int[] listY = new int[n];
    for (int i = 0; i < n; i++) {
      listX[i] = rand.nextInt(100); // random X between 0-99
      listY[i] = rand.nextInt(100); // random Y between 0-99
    }
    System.out.println("listX: " + java.util.Arrays.toString(listX));
    System.out.println("listY: " + java.util.Arrays.toString(listY));

    System.out.println(closest(listX, listY, 0, listX.length));
  }

}
