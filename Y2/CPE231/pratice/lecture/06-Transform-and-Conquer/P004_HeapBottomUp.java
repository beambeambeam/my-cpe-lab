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
    // Note: Converting from 1-based pseudocode to 0-based Java array
    // Pseudocode: i from floor(n/2) downto 1
    // Java: i from n/2-1 downto 0

    for (int i = n / 2 - 1; i >= 0; i--) {
      int k = i;
      int v = H[k];
      boolean heap = false;

      while (!heap && 2 * k + 1 < n) {
        int j = 2 * k + 1; // Left child in 0-based indexing
        if (j + 1 < n && H[j] < H[j + 1]) {
          j = j + 1; // Right child if larger
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
