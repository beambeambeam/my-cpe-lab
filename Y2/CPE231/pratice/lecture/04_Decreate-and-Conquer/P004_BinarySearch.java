public class P004_BinarySearch {
  private static int search(int[] A, int K) {
    int l = 0;
    int r = A.length - 1;

    while (l <= r) {
      int m = (l + r) / 2;
      if (K == A[m])
        return m;

      if (K < A[m]) {
        r = m - 1;
        continue;
      }

      l = m + 1;
    }

    return -1;
  }

  private static int searchRecursive(int[] A, int K, int l, int r) {
    if (l > r)
      return -1;
    int m = (l + r) / 2;
    if (K == A[m])
      return m;
    if (K < A[m])
      return searchRecursive(A, K, l, m - 1);
    return searchRecursive(A, K, m + 1, r);
  }

  public static int searchRecursive(int[] A, int K) {
    return searchRecursive(A, K, 0, A.length - 1);
  }

  public static void main(String[] args) {
    int[] list = { 3, 14, 27, 31, 39, 42, 55, 70, 74, 81, 85, 93, 98 };
    System.out.println(search(list, 70));
    System.out.println(searchRecursive(list, 70));
  }
}
