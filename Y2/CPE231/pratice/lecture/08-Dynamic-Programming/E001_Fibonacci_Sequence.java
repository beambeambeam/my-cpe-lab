public class E001_Fibonacci_Sequence {
  public static int fibonacciDP(int n) {
    if (n <= 1)
      return n;
    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
  }

  public static void main(String[] args) {
    java.util.Scanner scanner = new java.util.Scanner(System.in);

    System.out.print("Enter n to find nth Fibonacci number: ");
    int n = scanner.nextInt();

    int result = fibonacciDP(n);

    System.out.println("The " + n + "th Fibonacci number (DP) is: " + result);

    scanner.close();
  }
}
