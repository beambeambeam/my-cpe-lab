package kmutt.cpe.algorithm.cheatsheet;

// javac kmutt\cpe\algorithm\cheatsheet\Knapsack.java
// java kmutt.cpe.algorithm.cheatsheet.Knapsack

import java.util.Arrays;

// 0/1 Knapsack: n items (weight w[], value v[]), capacity W -> max value, each item taken at most once
// F(i,j) = max value with items 1..i, capacity j
// F(i,j) = F(i-1,j)                             if w[i] > j (skip)
// F(i,j) = max(F(i-1,j), v[i]+F(i-1,j-w[i]))   otherwise (skip vs take)
// Base: F(0,j)=0, F(i,0)=0 | O(nW) time, O(nW) space

public class Knapsack {

  public static int solve(int[] weights, int[] values, int capacity) {
    int n = weights.length;
    int[][] F = new int[n + 1][capacity + 1]; // F[i][j] = max value using items 1..i with capacity j

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= capacity; j++) {
        int wi = weights[i - 1], vi = values[i - 1]; // 0-indexed array
        if (wi > j) {
          F[i][j] = F[i - 1][j]; // item doesn't fit -> skip
        } else {
          F[i][j] = Math.max(F[i - 1][j], vi + F[i - 1][j - wi]); // max(skip, take)
        }
      }
    }
    return F[n][capacity];
  }

  public static int[] findSelectedItems(int[] weights, int[] values, int capacity) {
    int n = weights.length;
    int[][] F = new int[n + 1][capacity + 1];

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= capacity; j++) {
        int wi = weights[i - 1], vi = values[i - 1];
        if (wi > j)
          F[i][j] = F[i - 1][j];
        else
          F[i][j] = Math.max(F[i - 1][j], vi + F[i - 1][j - wi]);
      }
    }

    // Backtrack: if F[i][j] != F[i-1][j], item i was taken
    boolean[] selected = new boolean[n];
    int i = n, j = capacity;
    while (i > 0 && j > 0) {
      if (F[i][j] != F[i - 1][j]) { // value changed -> item was included
        selected[i - 1] = true;
        j -= weights[i - 1]; // reduce remaining capacity
      }
      i--;
    }

    int count = 0;
    for (boolean s : selected)
      if (s)
        count++;
    int[] result = new int[count];
    int idx = 0;
    for (int k = 0; k < n; k++)
      if (selected[k])
        result[idx++] = k;
    return result;
  }

  public static void main(String[] args) {
    int[] weights = { 2, 1, 3, 2 };
    int[] values = { 12, 10, 20, 15 };
    int capacity = 5;

    for (int i = 0; i < weights.length; i++)
      System.out.println(weights[i] + " " + values[i]);
    System.out.println();

    System.out.println(solve(weights, values, capacity));

    int[] selected = findSelectedItems(weights, values, capacity);
    System.out.println(Arrays.toString(selected));

    int tw = 0, tv = 0;
    for (int idx : selected) {
      tw += weights[idx];
      tv += values[idx];
    }
    System.out.println(tw + " " + tv);
  }
}
