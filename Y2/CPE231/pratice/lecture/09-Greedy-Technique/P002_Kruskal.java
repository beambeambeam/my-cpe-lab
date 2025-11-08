import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P002_Kruskal {
  static class Edge implements Comparable<Edge> {
    int from;
    int to;
    int weight;

    Edge(int from, int to, int weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
      return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
      return "(" + from + ", " + to + ", " + weight + ")";
    }
  }

  static class UnionFind {
    int[] parent;
    int[] rank;

    UnionFind(int n) {
      parent = new int[n];
      rank = new int[n];
      for (int i = 0; i < n; i++) {
        parent[i] = i;
        rank[i] = 0;
      }
    }

    int find(int x) {
      if (parent[x] != x) {
        parent[x] = find(parent[x]);
      }
      return parent[x];
    }

    boolean union(int x, int y) {
      int rootX = find(x);
      int rootY = find(y);

      if (rootX == rootY) {
        return false;
      }

      if (rank[rootX] < rank[rootY]) {
        parent[rootX] = rootY;
      } else if (rank[rootX] > rank[rootY]) {
        parent[rootY] = rootX;
      } else {
        parent[rootY] = rootX;
        rank[rootX]++;
      }

      return true;
    }
  }

  public static List<Edge> kruskal(int[][] graph) {
    int n = graph.length;
    List<Edge> E = new ArrayList<>();
    List<Edge> E_T = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (graph[i][j] > 0) {
          E.add(new Edge(i, j, graph[i][j]));
        }
      }
    }

    Collections.sort(E);

    UnionFind uf = new UnionFind(n);
    int ecounter = 0;
    int k = 0;

    while (ecounter < n - 1 && k < E.size()) {
      Edge e_ik = E.get(k);
      k++;

      if (uf.union(e_ik.from, e_ik.to)) {
        E_T.add(e_ik);
        ecounter++;
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

    List<Edge> mst = kruskal(graph);

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
