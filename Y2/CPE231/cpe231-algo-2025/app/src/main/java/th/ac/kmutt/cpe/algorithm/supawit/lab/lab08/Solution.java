package th.ac.kmutt.cpe.algorithm.supawit.lab.lab08;

import java.util.Scanner;

public class Solution {

  public static class Z {
    public static int[] readSequence(Scanner scanner) {
      int n = scanner.nextInt();
      int[] sequence = new int[n];
      for (int i = 0; i < n; i++) {
        sequence[i] = scanner.nextInt();
      }
      return sequence;
    }

    public static void printArrays(int[] dpAsc, int[] dpDesc) {
      for (int i = 0; i < dpAsc.length; i++) {
        System.out.print(dpAsc[i]);
        if (i < dpAsc.length - 1)
          System.out.print(" ");
      }
      System.out.println();

      for (int i = 0; i < dpDesc.length; i++) {
        System.out.print(dpDesc[i]);
        if (i < dpDesc.length - 1)
          System.out.print(" ");
      }
      System.out.println();
    }

    public static int[][] solve(int[] sequence) {
      int n = sequence.length;
      int[] dpAsc = new int[n];
      int[] dpDesc = new int[n];

      dpAsc[0] = 1;
      dpDesc[0] = 1;

      for (int i = 1; i < n; i++) {
        dpAsc[i] = 1;
        dpDesc[i] = 1;

        for (int j = 0; j < i; j++) {
          if (sequence[j] < sequence[i]) {
            dpAsc[i] = Math.max(dpAsc[i], dpDesc[j] + 1);
          } else if (sequence[j] > sequence[i]) {
            dpDesc[i] = Math.max(dpDesc[i], dpAsc[j] + 1);
          }
        }
      }

      return new int[][] { dpAsc, dpDesc };
    }

    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      int[] sequence = readSequence(scanner);
      scanner.close();

      int[][] result = solve(sequence);
      int[] dpAsc = result[0];
      int[] dpDesc = result[1];

      printArrays(dpAsc, dpDesc);
    }
  }

  public static class TheSpiritMerchant {
    public static int[] readInput(Scanner scanner) {
      int n = scanner.nextInt();
      int T = scanner.nextInt();
      int[] times = new int[n];
      int[] revenues = new int[n];

      for (int i = 0; i < n; i++) {
        times[i] = scanner.nextInt();
        revenues[i] = scanner.nextInt();
      }

      int[] result = new int[2 + 2 * n];
      result[0] = n;
      result[1] = T;
      for (int i = 0; i < n; i++) {
        result[2 + i] = times[i];
        result[2 + n + i] = revenues[i];
      }

      return result;
    }

    public static void printResult(int result) {
      System.out.println(result);
    }

    public static int solve(int n, int T, int[] times, int[] revenues) {
      int[][] dp = new int[n + 1][T + 1];

      for (int i = 1; i <= n; i++) {
        for (int w = 0; w <= T; w++) {
          if (times[i - 1] <= w) {
            dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - times[i - 1]] + revenues[i - 1]);
          } else {
            dp[i][w] = dp[i - 1][w];
          }
        }
      }

      return dp[n][T];
    }

    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      int[] input = readInput(scanner);
      scanner.close();

      int n = input[0];
      int T = input[1];
      int[] times = new int[n];
      int[] revenues = new int[n];

      for (int i = 0; i < n; i++) {
        times[i] = input[2 + i];
        revenues[i] = input[2 + n + i];
      }

      int result = solve(n, T, times, revenues);

      printResult(result);
    }
  }

  public static class EOD {
    public static int solve(int n, int[] blastRadius) {
      return 0;
    }

    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      scanner.close();
    }
  }
}
