import java.util.Arrays;

public class P002_DistributionCountingSort {

  public static int[] distributionCountingSort(int[] A, int l, int u) {
    int n = A.length;
    int range = u - l + 1;
    int[] D = new int[range];
    int[] S = new int[n];

    for (int j = 0; j < range; j++) {
      D[j] = 0;
    }

    for (int i = 0; i < n; i++) {
      D[A[i] - l] = D[A[i] - l] + 1;
    }

    for (int j = 1; j < range; j++) {
      D[j] = D[j - 1] + D[j];
    }

    for (int i = n - 1; i >= 0; i--) {
      int j = A[i] - l;
      S[D[j] - 1] = A[i];
      D[j] = D[j] - 1;
    }

    return S;
  }

  public static void main(String[] args) {
    int[] A = { 5, 2, 9, 1, 5, 6, 7, 3, 0, 4 };
    int l = 0;
    int u = 9;

    System.out.println("Original array: " + Arrays.toString(A));

    int[] sorted = distributionCountingSort(A, l, u);

    System.out.println("Sorted array:   " + Arrays.toString(sorted));
  }
}
