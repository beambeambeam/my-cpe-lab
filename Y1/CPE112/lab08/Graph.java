import java.util.*;

public abstract class Graph {
  protected int numVertices;
  protected Map<String, List<Edge>> adjacencyList;
  protected boolean isWeighted;

  public Graph(boolean isWeighted) {
    this.numVertices = 0;
    this.isWeighted = isWeighted;
    this.adjacencyList = new HashMap<>();
  }

  protected void addNode(String name) {
    if (!adjacencyList.containsKey(name)) {
      adjacencyList.put(name, new ArrayList<>());
    }
  }

  // fot unweighted graph
  public boolean addEdge(String source, String destination) {
    if (!isWeighted) {
      addEdgeWithWeight(source, destination, -1);
      return true;
    } else {
      return false;
    }
  }

  // for weighted graph
  public boolean addEdge(String source, String destination, int weight) {
    if (isWeighted) {
      addEdgeWithWeight(source, destination, weight);
      return true;
    } else {
      return false;
    }
  }

  public int getOutDegree(String name) {
    return adjacencyList.get(name).size();
  }

  public int getInDegree(String name) {
    int inDegree = 0;
    for (List<Edge> edges : adjacencyList.values()) {
      for (Edge edge : edges) {
        if (edge.destination.equals(name)) {
          inDegree++;
        }
      }
    }
    return inDegree;
  }

  public int sumWeightOutDegree(String name) {
    return adjacencyList.get(name).size();
  }

  public int sumWeightInDegree(String name) {
    return adjacencyList.get(name).size();
  }

  protected abstract void addEdgeWithWeight(String source, String destination, int weight);

  public void printGraph() {
    for (String vertex : adjacencyList.keySet()) {
      System.out.print(vertex + " -> ");
      List<Edge> edges = adjacencyList.get(vertex);
      for (Edge edge : edges) {
        System.out.print(edge.destination + (isWeighted ? "(" + edge.weight + ")" : "") + " ");
      }
      System.out.println();
    }
  }
}
