package th.ac.kmutt.cpe.algorithm.supawit.sorting;

import java.util.ArrayList;

public class CountingSort<T extends Comparable<T>> implements SortingStrategy<T> {

  public ArrayList<T> sorting(ArrayList<T> list) {
    if (list == null) {
      return new ArrayList<>();
    }
    if (list.size() <= 1) {
      return new ArrayList<>(list);
    }

    boolean allIntegers = true;
    for (T item : list) {
      if (item != null && !(item instanceof Integer)) {
        allIntegers = false;
        break;
      }
    }

    if (!allIntegers) {
      throw new UnsupportedOperationException("CountingSort only supports Integer types.");
    }

    return sortIntegerList(list);
  }

  private ArrayList<T> sortIntegerList(ArrayList<T> list) {
    ArrayList<Integer> intList = new ArrayList<>();
    for (T item : list) {
      intList.add((Integer) item);
    }

    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    for (Integer num : intList) {
      if (num != null) {
        min = Math.min(min, num);
        max = Math.max(max, num);
      }
    }

    if (min == Integer.MAX_VALUE) {
      return new ArrayList<>(list);
    }

    // Check for potential overflow in range calculation
    long longRange = (long) max - (long) min + 1;
    if (longRange > Integer.MAX_VALUE || longRange <= 0) {
      // Range too large for counting sort, fall back to a different approach
      throw new UnsupportedOperationException("Range too large for counting sort: " + longRange);
    }

    int range = (int) longRange;
    int[] count = new int[range];

    for (Integer num : intList) {
      if (num != null) {
        count[num - min]++;
      }
    }

    ArrayList<T> result = new ArrayList<>();

    long nullCount = intList.stream().mapToLong(x -> x == null ? 1 : 0).sum();
    for (int i = 0; i < nullCount; i++) {
      result.add(null);
    }

    for (int i = 0; i < range; i++) {
      int value = i + min;
      for (int j = 0; j < count[i]; j++) {
        result.add(findOriginalElement(list, value));
      }
    }

    return result;
  }

  private T findOriginalElement(ArrayList<T> originalList, int value) {
    for (T item : originalList) {
      if (item != null && ((Integer) item).intValue() == value) {
        return item;
      }
    }
    return null;
  }
}
