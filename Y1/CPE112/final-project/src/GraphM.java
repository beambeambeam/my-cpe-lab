// Implement your code in this file
public class GraphM extends AdjM {
  boolean isDirected;
  int numVertices;
  int numEdges;

  public GraphM(int n, boolean isDirected) {
    super(n);
    this.numVertices = n;
    this.isDirected = isDirected;
  }

  public void addEdge(int i, int j, int weight) {
    this.matrix[i][j] = weight;
    if (isDirected) {
      this.matrix[j][i] = weight;
    }
  }
}
