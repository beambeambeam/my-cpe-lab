package th.ac.kmutt.cpe.algorithm.supawit.sorting;

import java.util.ArrayList;

public interface SortingStrategy<T extends Comparable<T>> {
  ArrayList<T> sorting(ArrayList<T> list);
}
