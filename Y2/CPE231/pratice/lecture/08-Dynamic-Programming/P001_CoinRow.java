public class P001_CoinRow {
  public static int coinRow(int[] C) {
    int n = C.length;
    if (n == 0)
      return 0;
    if (n == 1)
      return C[0];

    int[] F = new int[n + 1];
    F[0] = 0;
    F[1] = C[0];

    for (int i = 2; i <= n; i++) {
      F[i] = Math.max(C[i - 1] + F[i - 2], F[i - 1]);
    }

    return F[n];
  }

  public static void main(String[] args) {
    java.util.Scanner scanner = new java.util.Scanner(System.in);

    System.out.print("Enter the number of coins: ");
    int n = scanner.nextInt();

    int[] C = new int[n];
    System.out.print("Enter coin values: ");
    for (int i = 0; i < n; i++) {
      C[i] = scanner.nextInt();
    }

    int result = coinRow(C);

    System.out.println("Maximum amount that can be picked up: " + result);

    scanner.close();
  }
}
