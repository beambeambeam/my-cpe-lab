import java.util.ArrayList;
import java.util.List;

public class P001_Prim {
  static class Edge {
    int from;
    int to;
    int weight;

    Edge(int from, int to, int weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }

    @Override
    public String toString() {
      return "(" + from + ", " + to + ", " + weight + ")";
    }
  }

  public static List<Edge> prim(int[][] graph) {
    int n = graph.length;
    List<Edge> E_T = new ArrayList<>();
    boolean[] V_T = new boolean[n];
    V_T[0] = true;

    for (int i = 1; i < n; i++) {
      int minWeight = Integer.MAX_VALUE;
      int v_star = -1;
      int u_star = -1;

      for (int v = 0; v < n; v++) {
        if (V_T[v]) {
          for (int u = 0; u < n; u++) {
            if (!V_T[u] && graph[v][u] > 0 && graph[v][u] < minWeight) {
              minWeight = graph[v][u];
              v_star = v;
              u_star = u;
            }
          }
        }
      }

      if (u_star != -1) {
        V_T[u_star] = true;
        E_T.add(new Edge(v_star, u_star, minWeight));
      }
    }

    return E_T;
  }

  public static void main(String[] args) {
    java.util.Scanner scanner = new java.util.Scanner(System.in);

    System.out.print("Enter the number of vertices: ");
    int n = scanner.nextInt();

    int[][] graph = new int[n][n];
    System.out.println("Enter the adjacency matrix (0 for no edge, weight for edge):");
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        graph[i][j] = scanner.nextInt();
      }
    }

    List<Edge> mst = prim(graph);

    System.out.println("Minimum Spanning Tree edges:");
    int totalWeight = 0;
    for (Edge e : mst) {
      System.out.println(e);
      totalWeight += e.weight;
    }
    System.out.println("Total weight: " + totalWeight);

    scanner.close();
  }
}
