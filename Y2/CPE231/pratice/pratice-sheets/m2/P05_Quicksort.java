import java.util.Scanner;

public class P05_Quicksort {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }

    quicksort(arr, 0, n - 1);

    for (int i = 0; i < n; i++) {
      System.out.print(arr[i]);
      if (i < n - 1)
        System.out.print(" ");
    }
    System.out.println();

    sc.close();
  }

  private static void quicksort(int[] A, int l, int r) {
    if (l < r) {
      int s = hoarePartition(A, l, r);
      quicksort(A, l, s - 1);
      quicksort(A, s + 1, r);
    }
  }

  private static int hoarePartition(int[] A, int l, int r) {
    int p = A[l];
    int i = l;
    int j = r + 1;

    while (true) {
      do {
        i++;
      } while (i <= r && A[i] < p);
      do {
        j--;
      } while (A[j] > p);

      if (i >= j)
        break;

      int temp = A[i];
      A[i] = A[j];
      A[j] = temp;
    }

    int temp = A[l];
    A[l] = A[j];
    A[j] = temp;

    return j;
  }
}
