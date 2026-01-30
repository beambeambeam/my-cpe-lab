
// Implement your code in this file.
import java.util.*;

public class GraphL extends AdjL {
  boolean isDirected;
  int numVertices;
  int numEdges;

  public GraphL(boolean isDirected, int numVertices, int numEdges) {
    this.isDirected = isDirected;
    this.numVertices = numVertices;
    this.numEdges = numEdges;
    this.adjacencyList = new HashMap<>();

    for (int i = 1; i <= numVertices; i++) {
      this.adjacencyList.put(i, new ArrayList<>());
    }
  }

  @Override
  public void addEdge(int src, int dest, int weight) {
    this.adjacencyList.putIfAbsent(src, new ArrayList<>());
    this.adjacencyList.get(src).add(new Pair<>(dest, weight));

    if (!isDirected) {
      this.adjacencyList.putIfAbsent(dest, new ArrayList<>());
      this.adjacencyList.get(dest).add(new Pair<>(src, weight));
    }
  }
}
