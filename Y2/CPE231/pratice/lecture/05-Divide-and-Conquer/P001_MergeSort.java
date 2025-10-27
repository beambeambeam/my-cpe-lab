public class P001_MergeSort {
  public static void merge(int[] list, int l, int r, int m) {
    int n1 = m - l + 1;
    int n2 = r - m;

    System.err.println(java.util.Arrays.toString(java.util.Arrays.copyOfRange(list, l, r + 1)));

    int[] L = new int[n1];
    int[] R = new int[n2];

    for (int i = 0; i < n1; ++i)
      L[i] = list[l + i];
    for (int j = 0; j < n2; ++j)
      R[j] = list[m + 1 + j];

    System.out.println(java.util.Arrays.toString(L));
    System.out.println(java.util.Arrays.toString(R));

    int i = 0, j = 0;

    int k = l;
    while (i < n1 && j < n2) {
      System.err.println("Comparing L[" + i + "]=" + L[i] + " and R[" + j + "]=" + R[j]);
      if (L[i] <= R[j]) {
        list[k] = L[i];
        System.err.println("Placing L[" + i + "]=" + L[i] + " at list[" + k + "]");
        i++;
      } else {
        list[k] = R[j];
        System.err.println("Placing R[" + j + "]=" + R[j] + " at list[" + k + "]");
        j++;
      }
      k++;
    }

    while (i < n1) {
      list[k] = L[i];
      System.err.println("Placing remaining L[" + i + "]=" + L[i] + " at list[" + k + "]");
      i++;
      k++;
    }

    while (j < n2) {
      list[k] = R[j];
      System.err.println("Placing remaining R[" + j + "]=" + R[j] + " at list[" + k + "]");
      j++;
      k++;
    }

    System.err.println("\n");
  }

  public static void sort(int[] list, int l, int r) {
    if (l < r) {
      int m = (l + r) / 2;
      sort(list, l, m);
      sort(list, m + 1, r);

      merge(list, l, r, m);
    }

  }

  public static void main(String[] args) {
    int[] list = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
    sort(list, 0, list.length - 1);
    System.out.println(java.util.Arrays.toString(list));

  }
}
