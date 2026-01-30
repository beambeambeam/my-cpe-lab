public abstract class AdjM {
  protected int[][] matrix;

  public AdjM(int n) {
    this.matrix = new int[n][n];
  }

  public abstract void addEdge(int i, int j, int weight);
}
