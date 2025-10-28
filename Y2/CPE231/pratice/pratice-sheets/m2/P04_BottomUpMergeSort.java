import java.util.Scanner;

public class P04_BottomUpMergeSort {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int n = scanner.nextInt();
    int[] A = new int[n];

    for (int i = 0; i < n; i++) {
      A[i] = scanner.nextInt();
    }

    bottomUpMergeSort(A, n);

    for (int i = 0; i < n; i++) {
      System.out.print(A[i]);
      if (i < n - 1) {
        System.out.print(" ");
      }
    }
    System.out.println();

    scanner.close();
  }

  private static void bottomUpMergeSort(int[] A, int n) {
    if (n < 2) {
      return;
    }

    int i = 1;
    while (i < n) {
      int j = 0;
      while (j < n - i) {
        if (n < j + (2 * i)) {
          merge(A, j, j + i, n);
        } else {
          merge(A, j, j + i, j + (2 * i));
        }
        j = j + 2 * i;
      }
      i = i * 2;
    }
  }

  private static void merge(int[] A, int left, int mid, int right) {
    int leftSize = mid - left;
    int rightSize = right - mid;

    int[] leftArray = new int[leftSize];
    int[] rightArray = new int[rightSize];

    for (int i = 0; i < leftSize; i++) {
      leftArray[i] = A[left + i];
    }
    for (int j = 0; j < rightSize; j++) {
      rightArray[j] = A[mid + j];
    }

    int i = 0;
    int j = 0;
    int k = left;

    while (i < leftSize && j < rightSize) {
      if (leftArray[i] <= rightArray[j]) {
        A[k] = leftArray[i];
        i++;
      } else {
        A[k] = rightArray[j];
        j++;
      }
      k++;
    }

    while (i < leftSize) {
      A[k] = leftArray[i];
      i++;
      k++;
    }

    while (j < rightSize) {
      A[k] = rightArray[j];
      j++;
      k++;
    }
  }
}
