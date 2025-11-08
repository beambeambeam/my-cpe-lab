public class P003_RobotCoinCollection {
  public static int robotCoinCollection(int[][] C) {
    int n = C.length;
    if (n == 0)
      return 0;
    int m = C[0].length;
    if (m == 0)
      return 0;

    int[][] F = new int[n][m];
    F[0][0] = C[0][0];

    for (int j = 1; j < m; j++) {
      F[0][j] = F[0][j - 1] + C[0][j];
    }

    for (int i = 1; i < n; i++) {
      F[i][0] = F[i - 1][0] + C[i][0];
    }

    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        F[i][j] = Math.max(F[i - 1][j], F[i][j - 1]) + C[i][j];
      }
    }

    return F[n - 1][m - 1];
  }

  public static void main(String[] args) {
    java.util.Scanner scanner = new java.util.Scanner(System.in);

    System.out.print("Enter the number of rows: ");
    int n = scanner.nextInt();
    System.out.print("Enter the number of columns: ");
    int m = scanner.nextInt();

    int[][] C = new int[n][m];
    System.out.println("Enter the board (1 for coin, 0 for no coin):");
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        C[i][j] = scanner.nextInt();
      }
    }

    int result = robotCoinCollection(C);

    System.out.println("Maximum number of coins collected: " + result);

    scanner.close();
  }
}
