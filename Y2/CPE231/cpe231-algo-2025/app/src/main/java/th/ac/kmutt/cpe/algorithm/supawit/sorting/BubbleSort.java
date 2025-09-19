package th.ac.kmutt.cpe.algorithm.supawit.sorting;

import java.util.ArrayList;

public class BubbleSort<T extends Comparable<T>> implements SortingStrategy<T> {

  @Override
  public ArrayList<T> sorting(ArrayList<T> list) {
    if (list == null) {
      return new ArrayList<>();
    }
    if (list.size() <= 1) {
      return new ArrayList<>(list);
    }

    ArrayList<T> result = new ArrayList<>(list);
    bubbleSort(result);
    return result;
  }

  private void bubbleSort(ArrayList<T> list) {
    int n = list.size();

    for (int i = 0; i < n - 1; i++) {
      boolean swapped = false;

      for (int j = 0; j < n - i - 1; j++) {
        if (compareElements(list.get(j), list.get(j + 1)) > 0) {
          swap(list, j, j + 1);
          swapped = true;
        }
      }

      if (!swapped) {
        break;
      }
    }
  }

  private void swap(ArrayList<T> list, int i, int j) {
    T temp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, temp);
  }

  private int compareElements(T a, T b) {
    if (a == null && b == null) {
      return 0;
    }
    if (a == null) {
      return -1; // null comes first
    }
    if (b == null) {
      return 1; // null comes first
    }
    return a.compareTo(b);
  }
}
