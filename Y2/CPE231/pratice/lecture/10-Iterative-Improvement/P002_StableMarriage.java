import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P002_StableMarriage {

  public static List<int[]> stableMarriage(
      int[][] menPreferences,
      int[][] womenPreferences) {
    int n = menPreferences.length;
    int[] womenMate = new int[n];
    int[] menNextProposal = new int[n];

    for (int i = 0; i < n; i++) {
      womenMate[i] = -1;
      menNextProposal[i] = 0;
    }

    Queue<Integer> freeMen = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      freeMen.offer(i);
    }

    while (!freeMen.isEmpty()) {
      int m = freeMen.poll();

      if (menNextProposal[m] < n) {
        int w = menPreferences[m][menNextProposal[m]];
        menNextProposal[m]++;

        if (womenMate[w] == -1) {
          womenMate[w] = m;
        } else {
          int currentMate = womenMate[w];
          if (prefers(womenPreferences[w], m, currentMate)) {
            womenMate[w] = m;
            freeMen.offer(currentMate);
          } else {
            freeMen.offer(m);
          }
        }
      }
    }

    List<int[]> matching = new ArrayList<>();
    for (int w = 0; w < n; w++) {
      if (womenMate[w] != -1) {
        matching.add(new int[] { womenMate[w], w });
      }
    }

    return matching;
  }

  private static boolean prefers(
      int[] womanPreferences,
      int man1,
      int man2) {
    for (int i = 0; i < womanPreferences.length; i++) {
      if (womanPreferences[i] == man1) {
        return true;
      }
      if (womanPreferences[i] == man2) {
        return false;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    java.util.Scanner scanner = new java.util.Scanner(System.in);

    System.out.print("Enter the number of men/women (n): ");
    int n = scanner.nextInt();

    int[][] menPreferences = new int[n][n];
    System.out.println(
        "Enter men's preferences (each man's preference list as n integers):");
    System.out.println(
        "For each man, enter the indices of women in order of preference (0 to " +
            (n - 1) +
            ")");
    for (int i = 0; i < n; i++) {
      System.out.print("Man " + i + " preferences: ");
      for (int j = 0; j < n; j++) {
        menPreferences[i][j] = scanner.nextInt();
      }
    }

    int[][] womenPreferences = new int[n][n];
    System.out.println(
        "Enter women's preferences (each woman's preference list as n integers):");
    System.out.println(
        "For each woman, enter the indices of men in order of preference (0 to " +
            (n - 1) +
            ")");
    for (int i = 0; i < n; i++) {
      System.out.print("Woman " + i + " preferences: ");
      for (int j = 0; j < n; j++) {
        womenPreferences[i][j] = scanner.nextInt();
      }
    }

    List<int[]> matching = stableMarriage(menPreferences, womenPreferences);

    System.out.println("Stable marriage matching:");
    for (int[] pair : matching) {
      System.out.println("Man " + pair[0] + " is matched with Woman " + pair[1]);
    }

    scanner.close();
  }
}
