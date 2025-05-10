
// Implement your code in this file
import java.util.*;

public class MyAlgorithm extends Algorithm {

  public MyAlgorithm() {

  }

  public List<Integer> preorder(BST tree) {
    List<Integer> result = new ArrayList<>();
    preorderTraversal(tree.root, result);
    return result;
  }

  private void preorderTraversal(BinNode node, List<Integer> result) {
    if (node == null) {
      return;
    }
    result.add(node.node);
    preorderTraversal(node.leftChild, result);
    preorderTraversal(node.rightChild, result);
  }

  public List<Integer> inorder(BST tree) {
    List<Integer> result = new ArrayList<>();
    inorderTraversal(tree.root, result);
    return result;
  }

  private void inorderTraversal(BinNode node, List<Integer> result) {
    if (node == null) {
      return;
    }

    inorderTraversal(node.leftChild, result);
    result.add(node.node);
    inorderTraversal(node.rightChild, result);
  }

  public List<Integer> postorder(BST tree) {
    List<Integer> result = new ArrayList<>();
    postorderTraversal(tree.root, result);
    return result;
  }

  private void postorderTraversal(BinNode node, List<Integer> result) {
    if (node == null) {
      return;
    }

    postorderTraversal(node.leftChild, result);
    postorderTraversal(node.rightChild, result);
    result.add(node.node);
  }

  public List<Integer> dfs(GraphM graph, int startNode) {
    List<Integer> result = new ArrayList<>();

    boolean[] visited = new boolean[graph.numVertices];
    Stack<Integer> stack = new Stack<>();
    stack.push(startNode);

    while (!stack.empty()) {
      int currentNode = stack.pop();

      if (!visited[currentNode]) {
        visited[currentNode] = true;
        result.add(currentNode);
      }

      for (int i = graph.numVertices - 1; i >= 0; i--) {
        if (graph.matrix[currentNode][i] != 0 && !visited[i]) {
          stack.push(i);
        }
      }
    }
    return result;
  }

  public List<Integer> bfs(GraphL graph, int startNode) {
    List<Integer> result = new ArrayList<>();
    boolean[] visited = new boolean[graph.numVertices];
    Queue<Integer> queue = new LinkedList<>();
    queue.add(startNode);
    visited[startNode] = true;

    while (!queue.isEmpty()) {
      int currentNode = queue.poll();
      result.add(currentNode);

      if (graph.adjacencyList.containsKey(currentNode)) {
        for (Pair<Integer, Integer> neighbor : graph.adjacencyList.get(currentNode)) {
          int neighborNode = neighbor.first;
          if (!visited[neighborNode]) {
            visited[neighborNode] = true;
            queue.add(neighborNode);
          }
        }
      }
    }
    return result;
  }

  public int floyd(GraphM graph, int startNode, int destNode) {
    int[][] dist = new int[graph.numVertices][graph.numVertices];

    for (int i = 0; i < graph.numVertices; i++) {
      for (int j = 0; j < graph.numVertices; j++) {
        if (i == j) {
          dist[i][j] = 0;
        } else if (graph.matrix[i][j] != 0) {
          dist[i][j] = graph.matrix[i][j];
        } else {
          dist[i][j] = Integer.MAX_VALUE;
        }
      }
    }

    for (int k = 0; k < graph.numVertices; k++) {
      for (int i = 0; i < graph.numVertices; i++) {
        if (dist[i][k] == Integer.MAX_VALUE)
          continue;
        for (int j = 0; j < graph.numVertices; j++) {
          if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
            dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
          }
        }
      }
    }

    return dist[startNode][destNode] == Integer.MAX_VALUE ? -1 : dist[startNode][destNode];
  }

  public boolean warshall(GraphM graph, int startNode, int destNode) {
    boolean[][] reach = new boolean[graph.numVertices][graph.numVertices];

    for (int i = 0; i < graph.numVertices; i++) {
      for (int j = 0; j < graph.numVertices; j++) {
        reach[i][j] = (i == j || graph.matrix[i][j] != 0);
      }
    }

    for (int k = 0; k < graph.numVertices; k++) {
      for (int i = 0; i < graph.numVertices; i++) {
        for (int j = 0; j < graph.numVertices; j++) {
          reach[i][j] |= (reach[i][k] && reach[k][j]);
        }
      }
    }

    return reach[startNode][destNode];
  }

  public int dijkstra(GraphL graph, int startNode, int destNode) {
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(pair -> pair[1]));
    int[] distances = new int[graph.numVertices + 1];
    Arrays.fill(distances, Integer.MAX_VALUE);
    distances[startNode] = 0;

    pq.add(new int[] { startNode, 0 });

    while (!pq.isEmpty()) {
      int[] current = pq.poll();
      int currentNode = current[0];

      if (currentNode == destNode)
        break;

      if (graph.adjacencyList.containsKey(currentNode)) {
        for (Pair<Integer, Integer> neighbor : graph.adjacencyList.get(currentNode)) {
          int neighborNode = neighbor.first;
          int weight = neighbor.second;

          if (distances[currentNode] + weight < distances[neighborNode]) {
            distances[neighborNode] = distances[currentNode] + weight;
            pq.add(new int[] { neighborNode, distances[neighborNode] });
          }
        }
      }
    }

    return distances[destNode] == Integer.MAX_VALUE ? -1 : distances[destNode];
  }

}
