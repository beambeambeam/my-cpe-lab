package th.ac.kmutt.cpe.algorithm.supawit.searching;

import java.util.ArrayList;

public interface SearchStrategy<T extends Comparable<T>> {
  public int search(ArrayList<T> list, T target);

}
