import java.util.ArrayList;
import java.util.Arrays;

public class P001_InsertionSort {
  private static void sort(ArrayList<Integer> A) {
    for (int i = 1; i < A.size(); i++) {
      int v = A.get(i);
      int j = i - 1;
      while (j >= 0 && A.get(j) > v) {
        A.set(j + 1, A.get(j));
        j--;
      }
      A.set(j + 1, v);
    }
  }

  public static void main(String[] args) {
    ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(89, 45, 68, 29, 34, 17));
    System.out.println(arr);
    sort(arr);
    System.out.println(arr);
  }
}
