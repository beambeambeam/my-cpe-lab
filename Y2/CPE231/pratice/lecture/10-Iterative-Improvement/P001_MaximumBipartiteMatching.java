import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P001_MaximumBipartiteMatching {

  public static List<int[]> maximumBipartiteMatching(
      int[][] graph,
      int vSize,
      int uSize) {
    int n = vSize + uSize;
    List<int[]> M = new ArrayList<>();
    int[] mate = new int[n];
    for (int i = 0; i < n; i++) {
      mate[i] = -1;
    }

    Queue<Integer> Q = new LinkedList<>();
    int[] label = new int[n];
    for (int i = 0; i < n; i++) {
      label[i] = -1;
    }

    while (true) {
      for (int i = 0; i < n; i++) {
        label[i] = -1;
      }

      for (int i = 0; i < vSize; i++) {
        if (mate[i] == -1) {
          Q.offer(i);
        }
      }

      boolean augmented = false;

      while (!Q.isEmpty() && !augmented) {
        int w = Q.poll();

        if (w < vSize) {
          for (int u = vSize; u < n; u++) {
            if (graph[w][u] == 1) {
              if (mate[u] == -1) {
                M.add(new int[] { w, u });
                mate[w] = u;
                mate[u] = w;

                int v = w;
                while (label[v] != -1) {
                  int prevU = label[v];
                  final int vFinal = v;
                  M.removeIf(e -> e[0] == vFinal && e[1] == prevU);
                  mate[v] = -1;
                  v = label[prevU];
                  M.add(new int[] { v, prevU });
                  mate[prevU] = v;
                  mate[v] = prevU;
                }

                for (int i = 0; i < n; i++) {
                  label[i] = -1;
                }

                Q.clear();
                for (int i = 0; i < vSize; i++) {
                  if (mate[i] == -1) {
                    Q.offer(i);
                  }
                }

                augmented = true;
                break;
              } else {
                boolean edgeInM = false;
                for (int[] edge : M) {
                  if (edge[0] == w && edge[1] == u) {
                    edgeInM = true;
                    break;
                  }
                }
                if (!edgeInM && label[u] == -1) {
                  label[u] = w;
                  Q.offer(u);
                }
              }
            }
          }
        } else {
          if (mate[w] != -1) {
            int v = mate[w];
            if (label[v] == -1) {
              label[v] = w;
              Q.offer(v);
            }
          }
        }
      }

      if (!augmented) {
        break;
      }
    }

    return M;
  }

  public static void main(String[] args) {
    java.util.Scanner scanner = new java.util.Scanner(System.in);

    System.out.print("Enter the size of set V: ");
    int vSize = scanner.nextInt();
    System.out.print("Enter the size of set U: ");
    int uSize = scanner.nextInt();

    int n = vSize + uSize;
    int[][] graph = new int[n][n];

    System.out.println(
        "Enter the bipartite graph as adjacency matrix:");
    System.out.println(
        "(1 for edge, 0 for no edge. Vertices 0 to " +
            (vSize - 1) +
            " are in V, vertices " +
            vSize +
            " to " +
            (n - 1) +
            " are in U)");

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        graph[i][j] = scanner.nextInt();
      }
    }

    List<int[]> matching = maximumBipartiteMatching(graph, vSize, uSize);

    System.out.println("Maximum matching edges:");
    for (int[] edge : matching) {
      System.out.println("(" + edge[0] + ", " + edge[1] + ")");
    }
    System.out.println("Matching size: " + matching.size());

    scanner.close();
  }
}
