import java.util.Arrays;

public class P001_InsertionSort {
  private static void insertionSort(int[] A) {
    int n = A.length;
    for (int i = 1; i < n; i++) {
      int v = A[i];
      int j = i - 1;
      while (j >= 0 && A[j] > v) {
        A[j + 1] = A[j];
        j--;
      }
      A[j + 1] = v;
    }
  }

  public static void main(String[] args) {
    int[] A = { 89, 45, 68, 29, 34, 17 };
    System.out.println(Arrays.toString(A));
    insertionSort(A);
    System.out.println(Arrays.toString(A));
  }
}
