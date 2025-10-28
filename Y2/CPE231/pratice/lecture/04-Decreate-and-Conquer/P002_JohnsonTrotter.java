import java.util.ArrayList;
import java.util.Arrays;

public class P002_JohnsonTrotter {
  private static ArrayList<int[]> permute(int n) {
    ArrayList<int[]> result = new ArrayList<>();
    int[] perm = new int[n];
    for (int i = 0; i < n; i++) {
      perm[i] = i + 1;
    }

    int[] dir = new int[n];
    for (int i = 0; i < n; i++)
      dir[i] = -1;

    result.add(perm.clone());

    while (true) {
      int mobile = -1, mobileIndex = -1;
      for (int i = 0; i < n; i++) {
        int next = i + dir[i];
        if (next >= 0 && next < n && perm[i] > perm[next]) {
          if (mobile == -1 || perm[i] > mobile) {
            mobile = perm[i];
            mobileIndex = i;
          }
        }
      }
      if (mobile == -1)
        break;

      int swapIndex = mobileIndex + dir[mobileIndex];
      int temp = perm[mobileIndex];
      perm[mobileIndex] = perm[swapIndex];
      perm[swapIndex] = temp;

      int tempDir = dir[mobileIndex];
      dir[mobileIndex] = dir[swapIndex];
      dir[swapIndex] = tempDir;

      mobileIndex = swapIndex;

      for (int i = 0; i < n; i++) {
        if (perm[i] > mobile) {
          dir[i] = -dir[i];
        }
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
