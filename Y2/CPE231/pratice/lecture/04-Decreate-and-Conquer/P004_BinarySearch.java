public class P004_BinarySearch {
  private static int binarySearch(int[] A, int K) {
    int n = A.length;
    int l = 0;
    int r = n - 1;

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

  private static int binarySearchRecursive(int[] A, int K, int l, int r) {
    if (l > r)
      return -1;
    int m = (l + r) / 2;
    if (K == A[m])
      return m;
    if (K < A[m])
      return binarySearchRecursive(A, K, l, m - 1);
    return binarySearchRecursive(A, K, m + 1, r);
  }

  public static int binarySearchRecursive(int[] A, int K) {
    int n = A.length;
    return binarySearchRecursive(A, K, 0, n - 1);
  }

  public static void main(String[] args) {
    int[] A = { 3, 14, 27, 31, 39, 42, 55, 70, 74, 81, 85, 93, 98 };
    System.out.println(binarySearch(A, 70));
    System.out.println(binarySearchRecursive(A, 70));
  }
}
