import java.util.Scanner;

public class FindMin {
  public static int minElement(int[] list) {
    if (list.length == 0) {
      return Integer.MAX_VALUE;
    }

    return min(0, list.length - 1, list);

  }

  public static int min(int i, int j, int[] list) {
    if (i == j) {
      return list[i];
    }

    int mid = (i + j) / 2;

    return Math.min(min(i, mid, list), min(mid + 1, j, list));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] l = new int[n];
    for (int i = 0; i < n; i++) {
      l[i] = scanner.nextInt();
    }

    int result = minElement(l);
    System.out.println("Minimum element: " + result);

    scanner.close();
  }

}
