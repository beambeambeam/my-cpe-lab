import java.util.Scanner;

public class P001_UniqueElements {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] l = new int[n];
    for (int i = 0; i < n; i++) {
      l[i] = scanner.nextInt();
    }

    boolean test = isUnique(l);
    System.out.println(test);

    scanner.close();

  }

  private static boolean isUnique(int[] l) {
    for (int i = 0; i < l.length - 1; i++) {
      for (int j = i + 1; j < l.length; j++) {
        if (l[i] == l[j]) {
          return false;
        }
      }
    }
    return true;
  }
}
