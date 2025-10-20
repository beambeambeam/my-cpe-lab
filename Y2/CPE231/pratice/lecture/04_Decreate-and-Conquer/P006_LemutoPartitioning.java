public class P006_LemutoPartitioning {
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
    int[] arr = { 4, 1, 10, 8, 7, 12, 9, 2, 15 };
    int pivotIndex = partition(arr, 0, arr.length - 1);
    System.out.println("Pivot index: " + pivotIndex);
    System.out.print("Partitioned array: ");
    for (int num : arr) {
      System.out.print(num + " ");
    }
    System.out.println();
  }
}
