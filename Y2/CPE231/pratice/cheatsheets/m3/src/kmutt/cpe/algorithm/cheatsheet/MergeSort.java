package kmutt.cpe.algorithm.cheatsheet;

import java.util.Arrays;
import java.util.Comparator;

// MergeSort: divide array in half, sort each, merge sorted halves
// Stable sort, always O(n log n) time, O(n) space for temp array
// T(n) = 2T(n/2) + O(n) -> O(n log n)

public class MergeSort {

  public static <T> void sort(T[] A, Comparator<? super T> cmp) {
    if (A.length <= 1)
      return;
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Object[A.length];
    mergeSort(A, temp, 0, A.length - 1, cmp);
  }

  // natural order for Comparable types
  public static <T extends Comparable<? super T>> void sort(T[] A) {
    sort(A, Comparator.naturalOrder());
  }

  private static <T> void mergeSort(T[] A, T[] temp, int lo, int hi, Comparator<? super T> cmp) {
    if (lo >= hi)
      return;
    int mid = lo + (hi - lo) / 2; // avoid overflow
    mergeSort(A, temp, lo, mid, cmp); // sort left half
    mergeSort(A, temp, mid + 1, hi, cmp); // sort right half
    merge(A, temp, lo, mid, hi, cmp); // merge sorted halves
  }

  private static <T> void merge(T[] A, T[] temp, int lo, int mid, int hi, Comparator<? super T> cmp) {
    for (int k = lo; k <= hi; k++)
      temp[k] = A[k]; // copy to temp

    int i = lo, j = mid + 1; // pointers for left and right
    for (int k = lo; k <= hi; k++) {
      if (i > mid)
        A[k] = temp[j++]; // left exhausted
      else if (j > hi)
        A[k] = temp[i++]; // right exhausted
      else if (cmp.compare(temp[i], temp[j]) <= 0)
        A[k] = temp[i++]; // take left (stable)
      else
        A[k] = temp[j++]; // take right
    }
  }

  public static void main(String[] args) {
    // natural order
    Integer[] nums = { 5, 2, 8, 1, 9 };
    sort(nums);
    System.out.println(Arrays.toString(nums));

    // reverse order
    Integer[] nums2 = { 5, 2, 8, 1, 9 };
    sort(nums2, Comparator.reverseOrder());
    System.out.println(Arrays.toString(nums2));

    // custom object by field
    String[][] people = { { "Alice", "30" }, { "Bob", "25" }, { "Eve", "28" } };
    sort(people, Comparator.comparingInt(p -> Integer.parseInt(p[1]))); // by age
    for (String[] p : people)
      System.out.println(p[0] + " " + p[1]);
  }
}

// Usage from another file:
// import kmutt.cpe.algorithm.cheatsheet.MergeSort;
// MergeSort.sort(array); // natural order (Comparable)
// MergeSort.sort(array, Comparator.reverseOrder()); // custom comparator
