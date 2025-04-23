public class DirectedGraph extends Graph {
  public DirectedGraph(boolean isWeighted) {
    super(isWeighted);
  }

  @Override
  protected void addEdgeWithWeight(String source, String destination, int weight) {
    addNode(source);
    addNode(destination);
    adjacencyList.get(source).add(new Edge(destination, weight));
  }
}
