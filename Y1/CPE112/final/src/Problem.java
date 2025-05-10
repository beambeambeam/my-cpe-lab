import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem {

  // Graph & Tree

  public static void Test() {
    MyAlgorithm algorithm = new MyAlgorithm();
    BST tree = new BST();

    Scanner input = new Scanner(System.in);

    int c = input.nextInt();
    for (int i = 0; i < c; i++) {
      tree.insertNode(input.nextInt());
    }

    List<Integer> result = algorithm.inorder(tree);

    System.out.println(result);

    input.close();
  }

  public static void MagicPowder() {
    Scanner input = new Scanner(System.in);
    Algorithm alg = new MyAlgorithm();

    int e = input.nextInt();
    int n = input.nextInt();
    input.nextLine();

    GraphL graph = new GraphL(false, e, n);

    int center = -1;

    for (int i = 0; i < n; i++) {
      int i1 = input.nextInt();
      String ops = input.next();
      int i2 = input.nextInt();
      graph.addEdge(i1, i2, 1);

      if (ops.equals("=")) {
        center = i2;
      }
    }

    input.close();

    ArrayList<Integer> leaf = new ArrayList<>();

    for (int node : graph.adjacencyList.keySet()) {
      if (graph.adjacencyList.get(node).size() == 1) {
        leaf.add(node);
      }
    }

    int shortest = Integer.MAX_VALUE;

    for (Integer l : leaf) {
      shortest = Math.min(shortest, alg.dijkstra(graph, center, l));
    }

    System.out.println(shortest + 1);
  }

  public static void Cokrabue() {
    Scanner input = new Scanner(System.in);

    Algorithm myAlgorithm = new MyAlgorithm();

    int v = input.nextInt();

    GraphM graph = new GraphM(v + 1, false);

    while (true) {
      String i1 = input.next();
      if (i1.equals("0"))
        break;
      String i2 = input.next();
      graph.addEdge(i1.charAt(0) - 64, i2.charAt(0) - 64, 1);
    }

    int x = input.nextInt();

    List<String> result = new ArrayList<>();

    for (int i = 0; i < x + 1; i++) {
      String j1 = input.next();
      String j2 = input.next();

      List<Integer> find = myAlgorithm.dfs(graph, j1.charAt(0) - 64);

      if (find.contains(j2.charAt(0) - 64)) {
        result.add("yes");
      } else {
        result.add("no");
      }
    }

    for (String r : result) {
      System.out.println(r);
    }

    input.close();
  }

  public static void NetworkEngineer() {
    Scanner input = new Scanner(System.in);
    int n = input.nextInt();
    int c = input.nextInt();

    GraphL graph = new GraphL(false, n, c);

    Algorithm alg = new MyAlgorithm();

    for (int i = 0; i < c; i++) {
      int x1 = input.nextInt();
      int x2 = input.nextInt();
      int w = input.nextInt();

      graph.addEdge(x1, x2, w);
    }

    int q = input.nextInt();
    List<Integer> result = new ArrayList<>();

    for (int i = 0; i < q; i++) {
      int s = input.nextInt();
      int max = Integer.MIN_VALUE;
      for (int node : graph.adjacencyList.keySet()) {
        if (node == i) {
          continue;
        }
        max = Math.max(max, alg.dijkstra(graph, s, node));
      }

      result.add(max);
    }

    input.close();

    for (Integer x : result) {
      System.out.println(x);
    }
  }
}
