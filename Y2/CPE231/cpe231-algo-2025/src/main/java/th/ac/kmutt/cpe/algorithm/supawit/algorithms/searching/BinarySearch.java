package th.ac.kmutt.cpe.algorithm.supawit.searching;

import java.util.ArrayList;

public class BinarySearch<T extends Comparable<T>> implements SearchStrategy<T> {
  public int search(ArrayList<T> list, T target) {
    return searchRecursive(list, target, 0, list.size() - 1);
  }

  private int searchRecursive(ArrayList<T> list, T target, int left, int right) {
    if (left > right) {
      return -1;
    }
    int mid = left + (right - left) / 2;
    T midVal = list.get(mid);
    int cmp = midVal.compareTo(target);

    if (cmp == 0) {
      return mid;
    } else if (cmp < 0) {
      return searchRecursive(list, target, mid + 1, right);
    } else {
      return searchRecursive(list, target, left, mid - 1);
    }
  }
}
