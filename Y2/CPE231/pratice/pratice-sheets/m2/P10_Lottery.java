import java.util.*;

public class P10_Lottery {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int m = sc.nextInt(); // length of bought string
    int n = sc.nextInt(); // length of winning string
    sc.nextLine();
    String text = sc.nextLine().trim();

    String pattern = sc.nextLine().trim();
    sc.close();

    if (text.length() != m || pattern.length() != n) {
      System.out.println("error");
      return;
    }

    Result res = horspool(text, pattern);
    if (res.found) {
      System.out.println("YES " + res.shifts + " " + res.position);
    } else {
      System.out.println("NO " + res.shifts + " -1");
    }
  }

  static class Result {
    boolean found;
    int shifts;
    int position;

    Result(boolean f, int s, int p) {
      found = f;
      shifts = s;
      position = p;
    }
  }

  private static Result horspool(String text, String pattern) {
    int n = text.length();
    int m = pattern.length();
    int[] shift = new int[256];

    Arrays.fill(shift, m);
    for (int i = 0; i < m - 1; i++) {
      shift[pattern.charAt(i)] = m - 1 - i;
    }

    int i = m - 1;
    int shifts = 0;
    while (i < n) {
      int k = 0;
      while (k < m && pattern.charAt(m - 1 - k) == text.charAt(i - k)) {
        k++;
      }
      if (k == m) {
        return new Result(true, shifts + 1, i - m + 1);
      }
      i += shift[text.charAt(i)];
      shifts++;
    }
    return new Result(false, shifts, -1);
  }
}
