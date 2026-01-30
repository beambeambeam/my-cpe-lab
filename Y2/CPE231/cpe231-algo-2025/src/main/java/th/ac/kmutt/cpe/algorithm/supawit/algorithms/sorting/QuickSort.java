package th.ac.kmutt.cpe.algorithm.supawit.sorting;

import java.util.ArrayList;

public class QuickSort<T extends Comparable<T>> implements SortingStrategy<T> {

  @Override
  public ArrayList<T> sorting(ArrayList<T> list) {
    if (list == null) {
      return new ArrayList<>();
    }
    if (list.size() <= 1) {
      return new ArrayList<>(list);
    }

    ArrayList<T> result = new ArrayList<>(list);
    quickSort(result, 0, result.size() - 1);
    return result;
  }

  private void quickSort(ArrayList<T> list, int low, int high) {
    if (low < high) {
      int pivotIndex = partition(list, low, high);

      quickSort(list, low, pivotIndex - 1);
      quickSort(list, pivotIndex + 1, high);
    }
  }

  private int partition(ArrayList<T> list, int low, int high) {
    T pivot = list.get(high);

    int i = low - 1;

    for (int j = low; j < high; j++) {
      if (compareElements(list.get(j), pivot) <= 0) {
        i++;
        swap(list, i, j);
      }
    }

    swap(list, i + 1, high);
    return i + 1;
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
      return -1;
    }
    if (b == null) {
      return 1;
    }
    return a.compareTo(b);
  }
}
