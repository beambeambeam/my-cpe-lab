import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Problem {
  public static void VindowsDuplicateMode() {
    Scanner input = new Scanner(System.in);

    BST tree = new BST();

    while (true) {
      int x = input.nextInt();
      if (x == 0) {
        break;
      }
      tree.insertNode(x);
    }

    BST duplicateTree = new BST();
    duplicateTree.root = duplicateTree(tree.root);

    input.close();
  }

  private static BinNode duplicateTree(BinNode node) {
    if (node == null) {
      return null;
    }
    BinNode newNode = new BinNode(node.node);
    newNode.leftChild = duplicateTree(node.leftChild);
    newNode.rightChild = duplicateTree(node.rightChild);
    return newNode;
  }

  public static void VindowsBalanceMode() {
    Scanner input = new Scanner(System.in);

    BST tree = new BST();

    while (true) {
      int x = input.nextInt();
      if (x == 0) {
        break;
      }
      tree.insertNode(x);
    }

    // get balance point

    int balance = input.nextInt();

    // find node of balance point

    BinNode current = tree.root;
    while (current != null) {
      int balanceFactor = getBalanceFactor(current);
      if (balanceFactor == balance) {
        System.out.println("Node with value " + current.node + " has balance factor " + balanceFactor);
        break;
      }
      if (balanceFactor > balance) {
        current = current.leftChild;
      } else {
        current = current.rightChild;
      }
    }

    input.close();
  }

  private static int height(BinNode node) {
    if (node == null) {
      return 0;
    }
    return Math.max(height(node.leftChild), height(node.rightChild)) + 1;
  }

  private static int getBalanceFactor(BinNode node) {
    if (node == null) {
      return 0;
    }
    return height(node.leftChild) - height(node.rightChild);
  }

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

  public static void SurvivalEp1() {
    Scanner input = new Scanner(System.in);

    Algorithm alg = new MyAlgorithm();

    int n = input.nextInt();

    GraphM graph = new GraphM(n + 1, false);

    for (int i = 0; i < n; i++) {
      int src = input.nextInt();
      int dst = input.nextInt();
      int dis = input.nextInt();

      graph.addEdge(src, dst, dis);
    }

    List<List<Integer>> result = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      List<Integer> sortedList = alg.dfs(graph, i);
      Collections.sort(sortedList);
      result.add(sortedList);
    }

    System.out.println("");

    for (int i = 0; i < result.size(); i++) {
      System.out.println("From Cave " + i + ", reachable caves:");
      System.out.println(result.get(i));
    }

    input.close();
  }

  public static void SurvivalEp2() {
    Scanner input = new Scanner(System.in);

    Algorithm alg = new MyAlgorithm();

    int n = input.nextInt();
    int m = input.nextInt();

    GraphL graph = new GraphL(false, n, m);

    for (int i = 0; i < m; i++) {
      int u = input.nextInt();
      int v = input.nextInt();

      graph.addEdge(u, v, 1);
    }

    int s = input.nextInt();
    int v = input.nextInt();
    int z = input.nextInt();

    if (alg.dijkstra(graph, s, v) > alg.dijkstra(graph, v, z)) {
      System.out.println("TOO LATE");
    } else {
      System.out.println("SAFE");
    }

    input.close();
  }
}
