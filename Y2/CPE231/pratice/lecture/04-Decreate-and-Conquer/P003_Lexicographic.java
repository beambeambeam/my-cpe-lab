import java.util.ArrayList;
import java.util.Collections;

public class P003_Lexicographic {
  private static ArrayList<ArrayList<Number>> permute(int n) {
    ArrayList<ArrayList<Number>> result = new ArrayList<>();
    ArrayList<Integer> perm = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      perm.add(i);
    }
    result.add(new ArrayList<>(perm));
    while (true) {
      int i = n - 2;
      while (i >= 0 && perm.get(i) >= perm.get(i + 1)) {
        i--;
      }
      if (i < 0)
        break;
      int j = n - 1;
      while (perm.get(j) <= perm.get(i)) {
        j--;
      }
      Collections.swap(perm, i, j);
      int start = i + 1, end = n - 1;
      while (start < end) {
        Collections.swap(perm, start, end);
        start++;
        end--;
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
