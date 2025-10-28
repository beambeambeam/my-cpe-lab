import java.util.Random;

public class P003_ShiftTable {

  public static int[] shiftTable(String pattern, int alphabetSize) {
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

  public static void main(String[] args) {
    Random random = new Random();

    int alphabetSize = 128;
    int patternLength = 6 + random.nextInt(5);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < patternLength; i++) {
      char c = (char) ('a' + random.nextInt(5));
      sb.append(c);
    }

    String pattern = sb.toString();
    System.out.println("Random Pattern: " + pattern);

    int[] table = shiftTable(pattern, alphabetSize);

    System.out.println("Shift Table for 'a'–'z':");
    for (char c = 'a'; c <= 'z'; c++) {
      System.out.printf("%c: %d%n", c, table[c]);
    }
  }
}
