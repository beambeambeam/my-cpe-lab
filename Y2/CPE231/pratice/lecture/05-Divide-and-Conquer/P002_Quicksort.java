public class P002_Quicksort {
  private static void sort(int[] list, int l, int r) {
    if (r <= l) {
      return;
    }

    int pivot = partition(list, l, r);
    sort(list, l, pivot - 1);
    sort(list, pivot + 1, r);
  }

  private static int partition(int[] list, int l, int r) {
    int pivot = list[r];
    int i = l - 1;

    for (int j = l; j < r; j++) {
      if (list[j] < pivot) {
        i++;
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
      }
    }

    i++;
    int temp = list[i];
    list[i] = list[r];
    list[r] = temp;

    return i;
  }

  public static void main(String[] args) {
    int[] list = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
    sort(list, 0, list.length - 1);
  }
}
