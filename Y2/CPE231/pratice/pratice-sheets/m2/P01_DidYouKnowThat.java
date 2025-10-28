import java.util.Scanner;

public class P01_DidYouKnowThat {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int m = scanner.nextInt();

    String[] words = new String[m];

    for (int i = 0; i < m; i++) {
      String s = scanner.next();

      if (s.length() != n) {
        System.err.println("Error: Word length must be " + n);
        scanner.close();
        return;
      }

      words[i] = s;
    }

    for (int i = 0; i < m; i++) {
      System.out.println(lexicographicRank(words[i]));
    }

    scanner.close();

  }

  private static int lexicographicRank(String s) {
    int n = s.length();
    int rank = 1;
    int[] count = new int[256];

    for (int i = 0; i < n; i++)
      count[s.charAt(i)]++;

    for (int i = 1; i < 256; i++)
      count[i] += count[i - 1];

    int[] fact = new int[n + 1];
    fact[0] = 1;
    for (int i = 1; i <= n; i++)
      fact[i] = fact[i - 1] * i;

    for (int i = 0; i < n; i++) {
      int charIndex = s.charAt(i);

      int smaller = count[charIndex - 1];
      rank += smaller * fact[n - i - 1];

      for (int j = charIndex; j < 256; j++)
        count[j]--;
    }

    return rank;
  }
}
