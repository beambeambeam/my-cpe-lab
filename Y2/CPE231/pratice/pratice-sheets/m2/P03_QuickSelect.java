import java.util.Scanner;

public class P03_QuickSelect {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int n = scanner.nextInt();
    int[] l = new int[n];

    for (int i = 0; i < n; i++) {
      l[i] = scanner.nextInt();
    }

    int k = scanner.nextInt();

    select(l, 0, l.length - 1, k - 1);

    System.out.println(l[k - 1]);

    scanner.close();

  }

  private static void select(int[] list, int l, int r, int k) {
    if (l == r) {
      return;
    }

    int j = l;

    int pivot = list[r];

    for (int i = l; i < r; i++) {
      if (list[i] <= pivot) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
        j++;
      }
    }

    int temp = list[r];
    list[r] = list[j];
    list[j] = temp;

    if (k < j) {
      select(list, l, j - 1, k);
    } else if (k > j) {
      select(list, j + 1, r, k);
    }
  }
}
