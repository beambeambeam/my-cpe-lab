import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P002_Kruskal {

    public static List<GraphUtils.Edge> kruskal(int[][] graph) {
        int n = graph.length;
        List<GraphUtils.Edge> E = new ArrayList<>();
        List<GraphUtils.Edge> E_T = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (graph[i][j] > 0) {
                    E.add(new GraphUtils.Edge(i, j, graph[i][j]));
                }
            }
        }

        Collections.sort(E);

        GraphUtils.UnionFind uf = new GraphUtils.UnionFind(n);
        int ecounter = 0;
        int k = 0;

        while (ecounter < n - 1 && k < E.size()) {
            GraphUtils.Edge e_ik = E.get(k);
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
        System.out.println(
            "Enter the adjacency matrix (0 for no edge, weight for edge):"
        );
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        List<GraphUtils.Edge> mst = kruskal(graph);

        System.out.println("Minimum Spanning Tree edges:");
        int totalWeight = 0;
        for (GraphUtils.Edge e : mst) {
            System.out.println(e);
            totalWeight += e.weight;
        }
        System.out.println("Total weight: " + totalWeight);

        scanner.close();
    }
}
