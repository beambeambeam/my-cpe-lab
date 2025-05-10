import java.util.*;

class Pair<U, V> implements Comparable<Pair<U, V>> {
  public final U first;
  public final V second;

  public Pair(U first, V second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public int compareTo(Pair<U, V> other) {
    int firstCompare = Integer.compare((Integer) this.first, (Integer) other.first);
    if (firstCompare != 0) {
      return firstCompare;
    }
    return Integer.compare((Integer) this.second, (Integer) other.second);
  }
}

// Usage with pair example:

// int a = sc.nextInt(); <-- ex. 10
// int b = sc.nextInt(); <- ex. 5
// Pair <Integer, Integer> p = new Pair<>(a, b)
// System.out.println("(" + p.first + "," + p.second + ")"); the output will be
// (10,5)

public abstract class AdjL {

  protected Map<Integer, List<Pair<Integer, Integer>>> adjacencyList;

  public AdjL() {
    this.adjacencyList = new HashMap<>();
  }

  public abstract void addEdge(int src, int dest, int weight);

}
