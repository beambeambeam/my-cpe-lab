import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class P002_JohnsonTrotter {
  private static ArrayList<ArrayList<Integer>> permute(int n) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    ArrayList<Integer> perm = IntStream.rangeClosed(1, n)
        .boxed()
        .collect(Collectors.toCollection(ArrayList::new));

    int[] dir = new int[n];
    for (int i = 0; i < n; i++)
      dir[i] = -1;

    result.add(new ArrayList<>(perm));

    while (true) {
      int mobile = -1, mobileIndex = -1;
      for (int i = 0; i < n; i++) {
        int next = i + dir[i];
        if (next >= 0 && next < n && perm.get(i) > perm.get(next)) {
          if (mobile == -1 || perm.get(i) > mobile) {
            mobile = perm.get(i);
            mobileIndex = i;
          }
        }
      }
      if (mobile == -1)
        break;

      int swapIndex = mobileIndex + dir[mobileIndex];
      int temp = perm.get(mobileIndex);
      perm.set(mobileIndex, perm.get(swapIndex));
      perm.set(swapIndex, temp);

      int tempDir = dir[mobileIndex];
      dir[mobileIndex] = dir[swapIndex];
      dir[swapIndex] = tempDir;

      mobileIndex = swapIndex;

      for (int i = 0; i < n; i++) {
        if (perm.get(i) > mobile) {
          dir[i] = -dir[i];
        }
      }

      result.add(new ArrayList<>(perm));
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(permute(3));
    System.out.println(permute(4));
  }
}
