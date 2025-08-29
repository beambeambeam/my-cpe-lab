package th.ac.kmutt.cpe.algorithm.supawit.searching;

import java.util.ArrayList;

public class SearchContext<T extends Comparable<T>> {
  private SearchStrategy<T> strategy;

  public SearchContext(SearchStrategy<T> strategy) {
    this.strategy = strategy;
  }

  public void setStrategy(SearchStrategy<T> strategy) {
    this.strategy = strategy;
  }

  public int executeSearch(ArrayList<T> data, T target) {
    return strategy.search(data, target);
  }

}
