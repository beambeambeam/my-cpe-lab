import java.util.Arrays;
import java.util.Scanner;

public class P002_PresortElementUniquesness {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] A = new int[n];
    for (int i = 0; i < n; i++) {
      A[i] = scanner.nextInt();
    }

    boolean test = presortElementUniqueness(A);
    System.out.println(test);

    scanner.close();
  }

  private static boolean presortElementUniqueness(int[] A) {
    int n = A.length;
    Arrays.sort(A);

    for (int i = 0; i < n - 1; i++) {
      if (A[i] == A[i + 1]) {
        return false;
      }
    }

    return true;
  }
}
