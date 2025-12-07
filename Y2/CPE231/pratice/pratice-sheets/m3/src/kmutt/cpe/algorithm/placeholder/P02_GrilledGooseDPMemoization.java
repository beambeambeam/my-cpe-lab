package kmutt.cpe.algorithm.placeholder;

import java.util.Arrays;
import java.util.Scanner;

public class P02_GrilledGooseDPMemoization {

  private static final int UNVISITED = -1;
  private static final int REACHABLE = 1;
  private static final int UNREACHABLE = 0;

  public static void main(String[] args) {
    runTests();
  }

  private static void runTests() {
    int[] boxes1 = { 8, 12, 15, 20 };
    int result1 = findLargestUnreachable(boxes1, 100);
    System.out.println("Test 1: x=100, boxes=" + Arrays.toString(boxes1));
    System.out.println("Expected: 49, Got: " + result1 + " -> " + (result1 == 49 ? "PASS" : "FAIL"));
    System.out.println();

    int[] boxes2 = { 25, 30, 12 };
    int result2 = findLargestUnreachable(boxes2, 123);
    System.out.println("Test 2: x=123, boxes=" + Arrays.toString(boxes2));
    System.out.println("Expected: 119, Got: " + result2 + " -> " + (result2 == 119 ? "PASS" : "FAIL"));
    System.out.println();

    int[] boxes3 = { 6, 9, 20 };
    int result3 = findLargestUnreachable(boxes3, 500);
    System.out.println("Test 3: x=500, boxes=" + Arrays.toString(boxes3));
    System.out.println("Expected: 43, Got: " + result3 + " -> " + (result3 == 43 ? "PASS" : "FAIL"));
    System.out.println();

    int[] boxes4 = { 1 };
    int result4 = findLargestUnreachable(boxes4, 100);
    System.out.println("Test 4: x=100, boxes=" + Arrays.toString(boxes4));
    System.out.println("Expected: 0 (all reachable), Got: " + result4 + " -> " + (result4 == 0 ? "PASS" : "FAIL"));
  }

  @SuppressWarnings("unused")
  private static void runInteractive() {
    Scanner sc = new Scanner(System.in);
    int x = sc.nextInt();
    int n = sc.nextInt();
    int[] boxes = new int[n];
    for (int i = 0; i < n; i++) {
      boxes[i] = sc.nextInt();
    }
    sc.close();

    int result = findLargestUnreachable(boxes, x);
    System.out.println(result);
  }

  private static int findLargestUnreachable(int[] boxes, int maxSum) {
    int[] memo = new int[maxSum + 1];
    Arrays.fill(memo, UNVISITED);

    for (int sum = 1; sum <= maxSum; sum++) {
      canReach(sum, boxes, memo);
    }

    for (int i = maxSum; i >= 1; i--) {
      if (memo[i] == UNREACHABLE) {
        return i;
      }
    }

    return 0;
  }

  private static boolean canReach(int sum, int[] boxes, int[] memo) {
    if (sum == 0) {
      return true;
    }

    if (sum < 0) {
      return false;
    }

    if (memo[sum] != UNVISITED) {
      return memo[sum] == REACHABLE;
    }

    for (int box : boxes) {
      if (box <= sum && canReach(sum - box, boxes, memo)) {
        memo[sum] = REACHABLE;
        return true;
      }
    }

    memo[sum] = UNREACHABLE;
    return false;
  }
}
