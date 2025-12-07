package kmutt.cpe.algorithm.placeholder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class P03_SalesmanDPTabular {
  private static final String START = "THA";
  private static final int NEG_INF = Integer.MIN_VALUE / 2;

  public static void main(String[] args) {
    runTests();
  }

  private static void runTests() {
    String[][] flights1 = {
        { "THA", "UK", "6" }, { "THA", "UAE", "4" }, { "THA", "USA", "8" },
        { "UK", "UAE", "2" }, { "UK", "USA", "3" }, { "UK", "SWE", "7" },
        { "UAE", "USA", "3" }, { "UAE", "SWE", "6" }, { "UAE", "DEN", "10" },
        { "USA", "SWE", "9" }, { "USA", "DEN", "5" }, { "SWE", "DEN", "9" }
    };
    Result r1 = solve(flights1);
    System.out.println("Test 1: 5 countries (UK, UAE, USA, SWE, DEN)");
    System.out.println("Path: " + r1.path);
    System.out.println("Expected cost: 44, Got: " + r1.cost + " -> " + (r1.cost == 44 ? "PASS" : "FAIL"));
    System.out.println();

    String[][] flights2 = {
        { "THA", "KOR", "12" }, { "THA", "CHI", "13" }, { "THA", "JAP", "6" }, { "THA", "RUS", "14" },
        { "KOR", "CHI", "3" }, { "KOR", "RUS", "12" },
        { "CHI", "JAP", "15" }, { "CHI", "RUS", "20" },
        { "JAP", "RUS", "14" }
    };
    Result r2 = solve(flights2);
    System.out.println("Test 2: 4 countries (KOR, CHI, JAP, RUS)");
    System.out.println("Path: " + r2.path);
    System.out.println("Expected cost: 66, Got: " + r2.cost + " -> " + (r2.cost == 66 ? "PASS" : "FAIL"));
    System.out.println();

    String[][] flights3 = {
        { "THA", "A", "10" }, { "THA", "B", "5" }, { "A", "B", "8" }
    };
    Result r3 = solve(flights3);
    System.out.println("Test 3: 2 countries (A, B) - simple triangle");
    System.out.println("Path: " + r3.path);
    System.out.println("Expected cost: 23 (THA->A->B->THA: 10+8+5), Got: " + r3.cost + " -> " + (r3.cost == 23 ? "PASS" : "FAIL"));
  }

  private static Result solve(String[][] flights) {
    Map<String, Map<String, Integer>> graph = new HashMap<>();
    List<String> cities = new ArrayList<>();
    cities.add(START);

    for (String[] flight : flights) {
      String from = flight[0];
      String to = flight[1];
      int cost = Integer.parseInt(flight[2]);

      graph.computeIfAbsent(from, k -> new HashMap<>()).put(to, cost);
      graph.computeIfAbsent(to, k -> new HashMap<>()).put(from, cost);

      if (!cities.contains(from)) cities.add(from);
      if (!cities.contains(to)) cities.add(to);
    }

    int n = cities.size();
    int[][] dist = buildDistanceMatrix(cities, graph);

    int[][] dp = new int[1 << n][n];
    int[][] parent = new int[1 << n][n];

    for (int mask = 0; mask < (1 << n); mask++) {
      for (int i = 0; i < n; i++) {
        dp[mask][i] = NEG_INF;
        parent[mask][i] = -1;
      }
    }

    dp[1][0] = 0;

    for (int mask = 1; mask < (1 << n); mask++) {
      if ((mask & 1) == 0) continue;

      for (int last = 0; last < n; last++) {
        if ((mask & (1 << last)) == 0) continue;
        if (dp[mask][last] == NEG_INF) continue;

        for (int next = 0; next < n; next++) {
          if ((mask & (1 << next)) != 0) continue;
          if (dist[last][next] == NEG_INF) continue;

          int newMask = mask | (1 << next);
          int newCost = dp[mask][last] + dist[last][next];

          if (newCost > dp[newMask][next]) {
            dp[newMask][next] = newCost;
            parent[newMask][next] = last;
          }
        }
      }
    }

    int fullMask = (1 << n) - 1;
    int maxCost = NEG_INF;
    int lastCity = -1;

    for (int i = 1; i < n; i++) {
      if (dp[fullMask][i] != NEG_INF && dist[i][0] != NEG_INF) {
        int totalCost = dp[fullMask][i] + dist[i][0];
        if (totalCost > maxCost) {
          maxCost = totalCost;
          lastCity = i;
        }
      }
    }

    List<String> path = reconstructPath(parent, cities, fullMask, lastCity);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < path.size(); i++) {
      if (i > 0) sb.append(" ");
      sb.append(path.get(i));
    }
    sb.append(" ").append(START);

    return new Result(sb.toString(), maxCost);
  }

  private static int[][] buildDistanceMatrix(List<String> cities, Map<String, Map<String, Integer>> graph) {
    int n = cities.size();
    int[][] dist = new int[n][n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i == j) {
          dist[i][j] = 0;
        } else {
          Integer cost = getEdgeCost(graph, cities.get(i), cities.get(j));
          dist[i][j] = (cost != null) ? cost : NEG_INF;
        }
      }
    }
    return dist;
  }

  private static Integer getEdgeCost(Map<String, Map<String, Integer>> graph, String from, String to) {
    Map<String, Integer> edges = graph.get(from);
    if (edges == null) return null;
    return edges.get(to);
  }

  private static List<String> reconstructPath(int[][] parent, List<String> cities, int mask, int last) {
    List<String> path = new ArrayList<>();

    while (last != -1) {
      path.add(0, cities.get(last));
      int prevLast = parent[mask][last];
      mask ^= (1 << last);
      last = prevLast;
    }

    return path;
  }

  private static class Result {
    String path;
    int cost;

    Result(String path, int cost) {
      this.path = path;
      this.cost = cost;
    }
  }

  @SuppressWarnings("unused")
  private static void runInteractive() {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int p = sc.nextInt();

    String[][] flights = new String[p][3];
    for (int i = 0; i < p; i++) {
      flights[i][0] = sc.next();
      flights[i][1] = sc.next();
      flights[i][2] = sc.next();
    }
    sc.close();

    Result result = solve(flights);
    System.out.println(result.path);
    System.out.println(result.cost);
  }
}
