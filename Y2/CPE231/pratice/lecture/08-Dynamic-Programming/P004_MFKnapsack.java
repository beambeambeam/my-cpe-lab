public class P004_MFKnapsack {
  private static int[] Weights;
  private static int[] Values;
  private static int[][] F;

  public static int mfKnapsack(int i, int j) {
    if (F[i][j] < 0) {
      int value;
      if (j < Weights[i - 1]) {
        value = mfKnapsack(i - 1, j);
      } else {
        value = Math.max(mfKnapsack(i - 1, j),
            Values[i - 1] + mfKnapsack(i - 1, j - Weights[i - 1]));
      }
      F[i][j] = value;
    }
    return F[i][j];
  }

  public static int solve(int[] weights, int[] values, int capacity) {
    int n = weights.length;
    Weights = weights;
    Values = values;

    F = new int[n + 1][capacity + 1];
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= capacity; j++) {
        if (i == 0 || j == 0) {
          F[i][j] = 0;
        } else {
          F[i][j] = -1;
        }
      }
    }

    return mfKnapsack(n, capacity);
  }

  public static void main(String[] args) {
    java.util.Scanner scanner = new java.util.Scanner(System.in);

    System.out.print("Enter the number of items: ");
    int n = scanner.nextInt();

    int[] weights = new int[n];
    int[] values = new int[n];

    System.out.println("Enter weights of items: ");
    for (int i = 0; i < n; i++) {
      weights[i] = scanner.nextInt();
    }

    System.out.println("Enter values of items: ");
    for (int i = 0; i < n; i++) {
      values[i] = scanner.nextInt();
    }

    System.out.print("Enter knapsack capacity: ");
    int capacity = scanner.nextInt();

    int result = solve(weights, values, capacity);

    System.out.println("Maximum value: " + result);

    scanner.close();
  }
}
