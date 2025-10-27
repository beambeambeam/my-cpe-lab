import java.util.Scanner;

public class P004_HeapBottomUp {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] H = new int[n];
    for (int i = 0; i < n; i++) {
      H[i] = scanner.nextInt();
    }

    Heapify(H);
    System.out.println(java.util.Arrays.toString(H));

    scanner.close();
  }

  private static int[] Heapify(int[] H) {
    int n = H.length;

    for (int i = n / 2 - 1; i >= 0; i--) {
      int k = i;
      int v = H[k];
      boolean heap = false;

      while (!heap && 2 * k + 1 < n) {
        int j = 2 * k + 1;
        if (j + 1 < n && H[j] < H[j + 1]) {
          j = j + 1;
        }
        if (v >= H[j]) {
          heap = true;
        } else {
          H[k] = H[j];
          k = j;
        }
      }
      H[k] = v;
    }

    return H;
  }
}
