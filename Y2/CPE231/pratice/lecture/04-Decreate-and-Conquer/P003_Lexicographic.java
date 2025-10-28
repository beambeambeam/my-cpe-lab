import java.util.ArrayList;
import java.util.Arrays;

public class P003_Lexicographic {
  private static ArrayList<int[]> permute(int n) {
    ArrayList<int[]> result = new ArrayList<>();
    int[] perm = new int[n];
    for (int i = 0; i < n; i++) {
      perm[i] = i + 1;
    }
    result.add(perm.clone());

    while (true) {
      int i = n - 2;
      while (i >= 0 && perm[i] >= perm[i + 1]) {
        i--;
      }
      if (i < 0)
        break;
      int j = n - 1;
      while (perm[j] <= perm[i]) {
        j--;
      }
      // Swap perm[i] and perm[j]
      int temp = perm[i];
      perm[i] = perm[j];
      perm[j] = temp;

      int start = i + 1, end = n - 1;
      while (start < end) {
        // Swap perm[start] and perm[end]
        temp = perm[start];
        perm[start] = perm[end];
        perm[end] = temp;
        start++;
        end--;
      }
      result.add(perm.clone());
    }
    return result;
  }

  public static void main(String[] args) {
    ArrayList<int[]> result3 = permute(3);
    for (int[] perm : result3) {
      System.out.println(Arrays.toString(perm));
    }

    ArrayList<int[]> result4 = permute(4);
    for (int[] perm : result4) {
      System.out.println(Arrays.toString(perm));
    }
  }
}
