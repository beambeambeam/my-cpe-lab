public class P002_Quicksort {
  private static void quicksort(int[] A, int l, int r) {
    if (l < r) {
      int s = partition(A, l, r);
      quicksort(A, l, s - 1);
      quicksort(A, s + 1, r);
    }
  }

  private static int partition(int[] A, int l, int r) {
    int p = A[l];
    int s = l;
    for (int i = l + 1; i <= r; i++) {
      if (A[i] < p) {
        s++;
        int temp = A[s];
        A[s] = A[i];
        A[i] = temp;
      }
    }
    int temp = A[l];
    A[l] = A[s];
    A[s] = temp;
    return s;
  }

  public static void main(String[] args) {
    int[] A = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
    quicksort(A, 0, A.length - 1);
    System.out.println(java.util.Arrays.toString(A));
  }
}
