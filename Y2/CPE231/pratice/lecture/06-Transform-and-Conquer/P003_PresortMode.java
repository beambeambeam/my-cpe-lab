import java.util.Arrays;
import java.util.Scanner;

public class P003_PresortMode {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] A = new int[n];
    for (int i = 0; i < n; i++) {
      A[i] = scanner.nextInt();
    }

    int mode = presortMode(A);
    System.out.println(mode);

    scanner.close();
  }

  private static int presortMode(int[] A) {
    int n = A.length;
    int modevalue = 0;
    Arrays.sort(A);
    int i = 0;
    int modefrequency = 0;
    while (i <= n - 1) {
      int runlength = 1;
      int runvalue = A[i];
      while (i + runlength <= n - 1 && A[i + runlength] == runvalue) {
        runlength++;
      }
      if (runlength > modefrequency) {
        modefrequency = runlength;
        modevalue = runvalue;
      }
      i = i + runlength;
    }
    return modevalue;
  }

}
