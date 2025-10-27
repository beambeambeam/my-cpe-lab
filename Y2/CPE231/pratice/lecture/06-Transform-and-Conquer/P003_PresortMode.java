import java.util.Arrays;
import java.util.Scanner;

public class P003_PresortMode {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] l = new int[n];
    for (int i = 0; i < n; i++) {
      l[i] = scanner.nextInt();
    }

    int mode = presortMode(l);
    System.out.println(mode);

    scanner.close();
  }

  private static int presortMode(int[] l) {
    int modevalue = 0;
    Arrays.sort(l);
    int i = 0;
    int modefrequency = 0;
    while (i <= l.length - 1) {
      int runlength = 1;
      int runvalue = l[i];
      while (i + runlength <= l.length - 1 && l[i + runlength] == runvalue) {
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
