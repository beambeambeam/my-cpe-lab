import java.util.ArrayList;
import java.util.Scanner;

public class P08_PresortedUniqueness {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }

    mergeSort(arr);
    int[] results = findUnique(arr, arr.length);

    System.out.println(java.util.Arrays.toString(results).replaceAll("[\\[\\],]", ""));

    sc.close();

  }

  public static void mergeSort(int[] a) {
    if (a == null || a.length < 2)
      return;
    int[] aux = new int[a.length];
    mergeSort(a, aux, 0, a.length - 1);
  }

  private static void mergeSort(int[] a, int[] aux, int left, int right) {
    if (left >= right)
      return;
    int mid = left + (right - left) / 2;
    mergeSort(a, aux, left, mid);
    mergeSort(a, aux, mid + 1, right);
    merge(a, aux, left, mid, right);
  }

  private static void merge(int[] a, int[] aux, int left, int mid, int right) {
    System.arraycopy(a, left, aux, left, right - left + 1);
    int i = left;
    int j = mid + 1;
    int k = left;
    while (i <= mid && j <= right) {
      if (aux[i] <= aux[j]) {
        a[k++] = aux[i++];
      } else {
        a[k++] = aux[j++];
      }
    }
    while (i <= mid)
      a[k++] = aux[i++];
  }

  private static int[] findUnique(int[] arr, int length) {
    if (length <= 0 || arr == null) {
      return new int[0];
    }

    ArrayList<Integer> unique = new ArrayList<>();
    unique.add(arr[0]);
    for (int i = 1; i < length; i++) {
      if (arr[i] != arr[i - 1]) {
        unique.add(arr[i]);
      }
    }

    int[] res = new int[unique.size()];
    for (int i = 0; i < res.length; i++) {
      res[i] = unique.get(i);
    }

    mergeSort(res);

    return res;
  }
}
