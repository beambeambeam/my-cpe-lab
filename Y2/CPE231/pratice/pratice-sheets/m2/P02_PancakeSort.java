import java.util.Scanner;

public class P02_PancakeSort {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] l = new int[n];
    for (int i = 0; i < n; i++) {
      l[i] = scanner.nextInt();
    }

    sort(l, n);
    System.out.println(java.util.Arrays.toString(l));

    scanner.close();
  }

  private static void sort(int[] l, int n) {
    if (n <= 1)
      return;

    int maxIdx = getMaxIdx(l, n);
    if (maxIdx != n - 1) {
      flip(l, maxIdx);
      flip(l, n - 1);
    }

    sort(l, n - 1);
  }

  private static void flip(int[] l, int k) {
    int i = 0;
    while (i < k) {
      int temp = l[i];
      l[i] = l[k];
      l[k] = temp;
      i++;
      k--;
    }
  }

  private static int getMaxIdx(int[] l, int n) {
    int maxIdx = 0;
    for (int i = 1; i < n; i++) {
      if (l[i] > l[maxIdx]) {
        maxIdx = i;
      }
    }
    return maxIdx;
  }
}
