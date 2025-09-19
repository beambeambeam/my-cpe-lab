package th.ac.kmutt.cpe.algorithm.supawit.sorting;

import java.util.ArrayList;

public class SortingContext<T extends Comparable<T>> {
  private SortingStrategy<T> strategy;

  public SortingContext(SortingStrategy<T> strategy) {
    this.strategy = strategy;
  }

  public void setStrategy(SortingStrategy<T> strategy) {
    this.strategy = strategy;
  }

  public ArrayList<T> executeSorting(ArrayList<T> data) {
    return strategy.sorting(data);
  }

}
