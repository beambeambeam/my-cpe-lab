
// Implement your code in this file
import java.util.*;

public class MyAlgorithm extends Algorithm {

  public MyAlgorithm() {

  }

  public List<Integer> preorder(BST tree) {
    ArrayList<Integer> result = new ArrayList<>();

    preorder(tree.root, result);

    return result;
  }

  private void preorder(BinNode node, List<Integer> result) {
    if (node == null) {
      return;
    }

    result.add(node.node);
    preorder(node.leftChild, result);
    preorder(node.rightChild, result);
  }

  public List<Integer> inorder(BST tree) {
    ArrayList<Integer> result = new ArrayList<>();

    inorder(tree.root, result);

    return result;
  }

  private void inorder(BinNode node, List<Integer> result) {
    if (node == null) {
      return;
    }

    inorder(node.leftChild, result);
    result.add(node.node);
    inorder(node.rightChild, result);
  }

  public List<Integer> postorder(BST tree) {
    ArrayList<Integer> result = new ArrayList<>();

    postorder(tree.root, result);

    return result;
  }

  private void postorder(BinNode node, List<Integer> result) {
    if (node == null) {
      return;
    }

    postorder(node.leftChild, result);
    postorder(node.rightChild, result);
    result.add(node.node);
  }

  public List<Integer> dfs(GraphM graph, int startNode) {
    ArrayList<Integer> result = new ArrayList<>();

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
    ArrayList<Integer> result = new ArrayList<>();

    boolean[] visited = new boolean[graph.numVertices];
    Queue<Integer> queue = new LinkedList<>();
    queue.add(startNode);
    visited[startNode] = true;

    while (!queue.isEmpty()) {
      int currentNode = queue.poll();
      result.add(currentNode);

      if (graph.adjacencyList.containsKey(currentNode)) {
        for (Pair<Integer, Integer> neighbour : graph.adjacencyList.get(currentNode)) {
          int neighbourNode = neighbour.first;
          if (!visited[neighbourNode]) {
            visited[neighbourNode] = true;
            queue.add(neighbourNode);
          }
        }
      }
    }

    return result;
  }

  public int bfsDistance(GraphL graph, int startNode, int destNode) {
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[graph.numVertices];
    int[] distance = new int[graph.numVertices];
    Arrays.fill(distance, -1);

    queue.add(startNode);
    visited[startNode] = true;
    distance[startNode] = 0;

    while (!queue.isEmpty()) {
      int currentNode = queue.poll();

      if (currentNode == destNode) {
        return distance[currentNode];
      }

      if (graph.adjacencyList.containsKey(currentNode)) {
        for (Pair<Integer, Integer> neighbour : graph.adjacencyList.get(currentNode)) {
          int neighbourNode = neighbour.first;
          if (!visited[neighbourNode]) {
            visited[neighbourNode] = true;
            distance[neighbourNode] = distance[currentNode] + 1;
            queue.add(neighbourNode);
          }
        }
      }
    }

    return -1;
  }

  public int floyd(GraphM graph, int startNode, int destNode) {
    int[][] dist = new int[graph.numVertices][graph.numVertices];

    for (int i = 0; i < dist.length; i++) {
      for (int j = 0; j < dist.length; j++) {
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

    for (int i = 0; i < reach.length; i++) {
      for (int j = 0; j < reach.length; j++) {
        reach[i][j] = (i == j || graph.matrix[i][j] != 0);
      }
    }

    for (int k = 0; k < graph.numVertices; k++) {
      for (int i = 0; i < graph.numVertices; i++) {
        for (int j = 0; j < graph.numVertices; j++) {
          reach[i][j] = reach[i][j] || (reach[i][k] && reach[k][j]);
        }
      }
    }

    return reach[startNode][destNode];
  }

  public int dijkstra(GraphL graph, int startNode, int destNode) {
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(pair -> pair[1]));
    int[] dist = new int[graph.numVertices];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[startNode] = 0;

    pq.add(new int[] { startNode, 0 });

    while (!pq.isEmpty()) {
      int[] current = pq.poll();
      int currentNode = current[0];
      int currentDist = current[1];

      if (currentNode == destNode) {
        return currentDist;
      }

      if (currentDist > dist[currentNode]) {
        continue;
      }

      if (graph.adjacencyList.containsKey(currentNode)) {
        for (Pair<Integer, Integer> neighbor : graph.adjacencyList.get(currentNode)) {
          int neighbourNode = neighbor.first;
          int weight = neighbor.second;

          if (dist[currentNode] + weight < dist[neighbourNode]) {
            dist[neighbourNode] = dist[currentNode] + weight;
            pq.add(new int[] { neighbourNode, dist[neighbourNode] });
          }
        }
      }
    }

    return dist[destNode] == Integer.MAX_VALUE ? -1 : dist[destNode];
  }

}
