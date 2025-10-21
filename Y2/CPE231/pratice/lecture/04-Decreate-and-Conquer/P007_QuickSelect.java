public class P007_QuickSelect {

  private static int lomutoPartition(int[] A, int l, int r) {
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

  public static int quickselect(int[] A, int l, int r, int k) {
    int s = lomutoPartition(A, l, r);

    if (s == l + k - 1) {
      return A[s];
    } else if (s > l + k - 1) {
      return quickselect(A, l, s - 1, k);
    } else {
      return quickselect(A, s + 1, r, k - (s - l) - 1);
    }
  }

  public static void main(String[] args) {
    int[] arr = { 4, 1, 10, 8, 7, 12, 9, 2, 15 };
    for (int i = 1; i <= arr.length; i++) {
      int[] temp = arr.clone();
      int kth = quickselect(temp, 0, temp.length - 1, i);
      System.out.println(i + ": " + kth);
    }
  }
}
