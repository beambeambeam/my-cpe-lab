import java.util.*;

public class P11_HashTable {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
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
    sc.close();

    for (int i = 0; i < Z; i++) {
      System.out.print(table[i]);
      if (i < Z - 1)
        System.out.print(" ");
    }
    System.out.println();
  }

  private static int hash(String s, int Z) {
    int sum = 0;
    for (char c : s.toCharArray()) {
      sum += (Character.toUpperCase(c) - 'A' + 1);
    }
    return sum % Z;
  }
}
