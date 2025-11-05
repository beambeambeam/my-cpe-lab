import java.util.*;

public class HashTableHorspool {
  // HashTable logic
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // HashTable input
    int Z = sc.nextInt();
    int n = sc.nextInt();
    sc.nextLine();
    String[] table = new String[Z];
    Arrays.fill(table, "NULL");
    for (int i = 0; i < n; i++) {
      String word = sc.nextLine().trim();
      int index = hash(word, Z);
      while (!table[index].equals("NULL")) {
        index = (index + 1) % Z;
      }
      table[index] = word;
    }
    // Print HashTable
    for (int i = 0; i < Z; i++) {
      System.out.print(table[i]);
      if (i < Z - 1)
        System.out.print(" ");
    }
    System.out.println();

    // Now use Horspool to search for a pattern in the hash table contents
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < Z; i++) {
      if (!table[i].equals("NULL")) {
        sb.append(table[i]);
        sb.append(" ");
      }
    }
    String hashTableText = sb.toString().trim();

    // Input pattern to search for
    String pattern = sc.nextLine().trim();
    int m = pattern.length();
    int nText = hashTableText.length();
    if (m == 0 || nText == 0) {
      System.out.println("error");
      sc.close();
      return;
    }
    Result res = horspool(hashTableText, pattern);
    if (res.found) {
      System.out.println("YES " + res.shifts + " " + res.position);
    } else {
      System.out.println("NO " + res.shifts + " -1");
    }
    sc.close();
  }

  private static int hash(String s, int Z) {
    int sum = 0;
    for (char c : s.toCharArray()) {
      sum += (Character.toUpperCase(c) - 'A' + 1);
    }
    return sum % Z;
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
