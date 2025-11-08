public class P002_ChangeMaking {
  public static int changeMaking(int[] D, int n) {
    int m = D.length;
    int[] F = new int[n + 1];
    F[0] = 0;

    for (int i = 1; i <= n; i++) {
      int temp = Integer.MAX_VALUE;
      int j = 0;
      while (j < m && i >= D[j]) {
        temp = Math.min(F[i - D[j]], temp);
        j++;
      }
      F[i] = temp + 1;
    }

    return F[n];
  }

  public static void main(String[] args) {
    java.util.Scanner scanner = new java.util.Scanner(System.in);

    System.out.print("Enter the number of coin denominations: ");
    int m = scanner.nextInt();

    int[] D = new int[m];
    System.out.print("Enter coin denominations (must be sorted, first must be 1): ");
    for (int i = 0; i < m; i++) {
      D[i] = scanner.nextInt();
    }

    System.out.print("Enter the target amount: ");
    int n = scanner.nextInt();

    int result = changeMaking(D, n);

    System.out.println("Minimum number of coins needed: " + result);

    scanner.close();
  }
}
