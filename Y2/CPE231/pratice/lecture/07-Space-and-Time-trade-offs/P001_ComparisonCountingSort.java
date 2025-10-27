public class P001_ComparisonCountingSort {
  public static void main(String[] args) {
    int[] A = new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
    int[] sorted = sort(A);

    System.out.println(java.util.Arrays.toString(sorted));
  }

  private static int[] sort(int[] A) {
    int[] S = new int[A.length];
    int[] Count = new int[A.length];
    java.util.Arrays.fill(Count, 0);

    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < A.length; j++) {
        if (A[j] < A[i]) {
          Count[i] = Count[i] + 1;
        }
      }
    }

    for (int i = 0; i < Count.length; i++) {
      S[Count[i]] = A[i];
    }

    return S;
  }

}
