import java.util.Scanner;

public class P09_HeapSort {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }

    for (int i = n; i > 1; i--) {
      heapify(arr, i);
      int temp = arr[0];
      arr[0] = arr[i - 1];
      arr[i - 1] = temp;
    }

    for (int i = 0; i < n; i++) {
      System.out.print(arr[i]);
      if (i < n - 1)
        System.out.print(" ");
    }
    System.out.println();

    for (int i = n - 1; i >= 0; i--) {
      System.out.print(arr[i]);
      if (i > 0)
        System.out.print(" ");
    }
    System.out.println();
    sc.close();
  }

  private static void heapify(int[] A, int n) {
    for (int i = n / 2 - 1; i >= 0; i--) {
      int k = i;
      int v = A[k];
      boolean heap = false;
      while (!heap && 2 * k + 1 < n) {
        int j = 2 * k + 1; // left child
        if (j + 1 < n) {
          if (A[j] > A[j + 1])
            j++;
        }
        if (v <= A[j]) {
          heap = true;
        } else {
          A[k] = A[j];
          k = j;
        }
      }
      A[k] = v;
    }
  }

}
