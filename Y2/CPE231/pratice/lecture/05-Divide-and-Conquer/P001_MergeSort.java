public class P001_MergeSort {
  public static void merge(int[] B, int[] C, int[] A) {
    int p = B.length;
    int q = C.length;
    int i = 0, j = 0, k = 0;

    while (i < p && j < q) {
      if (B[i] <= C[j]) {
        A[k] = B[i];
        i++;
      } else {
        A[k] = C[j];
        j++;
      }
      k++;
    }

    if (i == p) {
      while (j < q) {
        A[k] = C[j];
        j++;
        k++;
      }
    } else {
      while (i < p) {
        A[k] = B[i];
        i++;
        k++;
      }
    }
  }

  public static void mergeSort(int[] A) {
    int n = A.length;
    if (n > 1) {
      int[] B = new int[n/2];
      int[] C = new int[(n+1)/2];
      
      for (int i = 0; i < n/2; i++) {
        B[i] = A[i];
      }
      for (int i = 0; i < (n+1)/2; i++) {
        C[i] = A[n/2 + i];
      }
      
      mergeSort(B);
      mergeSort(C);
      merge(B, C, A);
    }
  }

  public static void main(String[] args) {
    int[] A = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
    mergeSort(A);
    System.out.println(java.util.Arrays.toString(A));
  }
}
