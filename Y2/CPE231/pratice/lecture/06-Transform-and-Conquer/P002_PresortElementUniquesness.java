import java.util.Scanner;

public class P002_PresortElementUniquesness {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] l = new int[n];
    for (int i = 0; i < n; i++) {
      l[i] = scanner.nextInt();
    }

    boolean test = presort(l);
    System.out.println(test);

    scanner.close();

  }

  private static boolean presort(int[] l) {
    for (int i = 0; i < l.length - 2; i++) {
      if (l[i] == l[i + 1]) {
        return false;
      }
    }

    return true;
  }
}
