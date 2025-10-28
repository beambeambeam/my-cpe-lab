public class P001_ComparisonCountingSort {
  public static void main(String[] args) {
    int[] A = new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
    int[] sorted = comparisonCountingSort(A);

    System.out.println(java.util.Arrays.toString(sorted));
  }

  private static int[] comparisonCountingSort(int[] A) {
    int n = A.length;
    int[] S = new int[n];
    int[] Count = new int[n];

    for (int i = 0; i < n; i++) {
      Count[i] = 0;
    }

    for (int i = 0; i < n - 1; i++) {
      for (int j = i + 1; j < n; j++) {
        if (A[i] < A[j]) {
          Count[j] = Count[j] + 1;
        } else {
          Count[i] = Count[i] + 1;
        }
      }
    }

    for (int i = 0; i < n; i++) {
      S[Count[i]] = A[i];
    }

    return S;
  }
}
