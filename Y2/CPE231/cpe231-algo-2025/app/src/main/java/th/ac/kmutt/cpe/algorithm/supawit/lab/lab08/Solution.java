package th.ac.kmutt.cpe.algorithm.supawit.lab.lab08;

import java.util.Scanner;

public class Solution {

  public static class Z {
    public static class InputReader {
      private Scanner scanner;

      public InputReader() {
        this.scanner = new Scanner(System.in);
      }

      public int[] readSequence() {
        int n = scanner.nextInt();
        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {
          sequence[i] = scanner.nextInt();
        }
        return sequence;
      }

      public void close() {
        if (scanner != null) {
          scanner.close();
        }
      }
    }

    public static class OutputPrinter {
      public void printArrays(int[] dpAsc, int[] dpDesc) {
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
      InputReader reader = new InputReader();
      int[] sequence = reader.readSequence();
      reader.close();

      int[][] result = solve(sequence);
      int[] dpAsc = result[0];
      int[] dpDesc = result[1];

      OutputPrinter printer = new OutputPrinter();
      printer.printArrays(dpAsc, dpDesc);
    }
  }

  public static class TheSpiritMerchant {
    public static class InputReader {
      // User will implement
    }

    public static class OutputPrinter {
      // User will implement
    }

    public static int solve(int n, int T, int[] times, int[] revenues) {
      // User will implement
      return 0;
    }

    public static void main(String[] args) {
      InputReader reader = new InputReader();
      // User reads input via InputReader methods

      // int result = solve(...);

      OutputPrinter printer = new OutputPrinter();
      // printer.printResult(result);
    }
  }

  public static class EOD {
    public static class InputReader {
      // User will implement
    }

    public static class OutputPrinter {
      // User will implement
    }

    public static int solve(int n, int[] blastRadius) {
      // User will implement
      return 0;
    }

    public static void main(String[] args) {
      InputReader reader = new InputReader();
      // User reads input via InputReader methods

      // int result = solve(...);

      OutputPrinter printer = new OutputPrinter();
      // printer.printResult(result);
    }
  }
}
