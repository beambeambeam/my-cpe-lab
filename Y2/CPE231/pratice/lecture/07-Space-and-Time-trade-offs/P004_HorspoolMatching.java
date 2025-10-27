
public class P004_HorspoolMatching {

  public static int[] ShiftTable(String pattern, int alphabetSize) {
    int m = pattern.length();
    int[] table = new int[alphabetSize];

    for (int i = 0; i < alphabetSize; i++) {
      table[i] = m;
    }

    for (int j = 0; j < m - 1; j++) {
      char c = pattern.charAt(j);
      table[c] = m - 1 - j;
    }

    return table;
  }

  public static int HorspoolMatching(String pattern, String text) {
    int m = pattern.length();
    int n = text.length();
    int[] table = ShiftTable(pattern, 128);

    int i = m - 1;
    while (i <= n - 1) {
      int k = 0;
      while (k <= m - 1 && pattern.charAt(m - 1 - k) == text.charAt(i - k)) {
        k++;
      }
      if (k == m) {
        return i - m + 1;
      } else {
        i = i + table[text.charAt(i)];
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    String text1 = "ababcababcabcab";
    String pattern1 = "abcab";
    System.out.println("CASE 1 (Found):");
    System.out.println("Text:    " + text1);
    System.out.println("Pattern: " + pattern1);
    int pos1 = HorspoolMatching(pattern1, text1);
    if (pos1 == -1) {
      System.out.println("Pattern not found.\n");
    } else {
      System.out.println("Pattern found at index: " + pos1);
      System.out.println("Matched substring: " + text1.substring(pos1, pos1 + pattern1.length()) + "\n");
    }

    String text2 = "aaabbbcccdddeee";
    String pattern2 = "abcd";
    System.out.println("CASE 2 (Not Found):");
    System.out.println("Text:    " + text2);
    System.out.println("Pattern: " + pattern2);
    int pos2 = HorspoolMatching(pattern2, text2);
    if (pos2 == -1) {
      System.out.println("Pattern not found.\n");
    } else {
      System.out.println("Pattern found at index: " + pos2);
      System.out.println("Matched substring: " + text2.substring(pos2, pos2 + pattern2.length()) + "\n");
    }
  }
}
