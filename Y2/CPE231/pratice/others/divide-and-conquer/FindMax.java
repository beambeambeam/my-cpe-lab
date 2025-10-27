import java.util.Scanner;

public class FindMax {
  public static int maxElement(int[] list) {
    if (list.length == 0) {
      return Integer.MIN_VALUE;
    }

    return max(0, list.length - 1, list);
  }

  public static int max(int i, int j, int[] list) {
    if (i == j) {
      return list[i];
    }
    int mid = (i + j) / 2;
    return Math.max(max(i, mid, list), max(mid + 1, j, list));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] l = new int[n];
    for (int i = 0; i < n; i++) {
      l[i] = scanner.nextInt();
    }

    int result = maxElement(l);
    System.out.println("Maximum element: " + result);

    scanner.close();
  }
}
