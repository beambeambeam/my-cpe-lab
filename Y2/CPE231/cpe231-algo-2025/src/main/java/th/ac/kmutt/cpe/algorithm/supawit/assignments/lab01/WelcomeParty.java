import java.util.*;
import java.io.*;

public class WelcomeParty {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    int[] plants = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int[] pots = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    int result = solve(plants, pots, n);
    System.out.println(result);
  }

  private static int solve(int[] plants, int[] pots, int n) {
    // Sort both arrays
    Arrays.sort(plants);
    Arrays.sort(pots);

    int minResult = Integer.MAX_VALUE;

    // Strategy 1: Pair smallest with smallest
    minResult = Math.min(minResult, calculateDifference(plants, pots, n, false));

    // Strategy 2: Pair smallest plant with largest pot
    minResult = Math.min(minResult, calculateDifference(plants, pots, n, true));

    return minResult;
  }

  private static int calculateDifference(int[] plants, int[] pots, int n, boolean reverse) {
    int[] heights = new int[n];

    if (reverse) {
      for (int i = 0; i < n; i++) {
        heights[i] = plants[i] + pots[n - 1 - i];
      }
    } else {
      for (int i = 0; i < n; i++) {
        heights[i] = plants[i] + pots[i];
      }
    }

    Arrays.sort(heights);

    int sum = 0;
    for (int i = 1; i < n; i++) {
      sum += heights[i] - heights[i - 1];
    }

    return sum;
  }
}
