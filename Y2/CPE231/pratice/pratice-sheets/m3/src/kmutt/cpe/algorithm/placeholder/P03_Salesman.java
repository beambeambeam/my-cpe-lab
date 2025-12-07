package kmutt.cpe.algorithm.placeholder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class P03_Salesman {
  private static final String START = "THA";

  public static void main(String[] args) {
    runTests();
    // runInteractive();
  }

  private static void runTests() {
    // Test 1: 5 countries, 12 flights → Expected cost: 44
    String[][] flights1 = {
        { "THA", "UK", "6" }, { "THA", "UAE", "4" }, { "THA", "USA", "8" },
        { "UK", "UAE", "2" }, { "UK", "USA", "3" }, { "UK", "SWE", "7" },
        { "UAE", "USA", "3" }, { "UAE", "SWE", "6" }, { "UAE", "DEN", "10" },
        { "USA", "SWE", "9" }, { "USA", "DEN", "5" }, { "SWE", "DEN", "9" }
    };
    Result r1 = solve(flights1);
    System.out.println("Test 1: 5 countries (UK, UAE, USA, SWE, DEN)");
    System.out.println("Path: " + r1.path);
    System.out.println("Expected cost: 44, Got: " + r1.cost + " → " + (r1.cost == 44 ? "PASS" : "FAIL"));
    System.out.println();

    // Test 2: 4 countries → Expected cost: 66
    String[][] flights2 = {
        { "THA", "KOR", "12" }, { "THA", "CHI", "13" }, { "THA", "JAP", "6" }, { "THA", "RUS", "14" },
        { "KOR", "CHI", "3" }, { "KOR", "RUS", "12" },
        { "CHI", "JAP", "15" }, { "CHI", "RUS", "20" },
        { "JAP", "RUS", "14" }
    };
    Result r2 = solve(flights2);
    System.out.println("Test 2: 4 countries (KOR, CHI, JAP, RUS)");
    System.out.println("Path: " + r2.path);
    System.out.println("Expected cost: 66, Got: " + r2.cost + " → " + (r2.cost == 66 ? "PASS" : "FAIL"));
    System.out.println();

    // Test 3: Simple triangle
    String[][] flights3 = {
        { "THA", "A", "10" }, { "THA", "B", "5" }, { "A", "B", "8" }
    };
    Result r3 = solve(flights3);
    System.out.println("Test 3: 2 countries (A, B) - simple triangle");
    System.out.println("Path: " + r3.path);
    System.out.println("Expected cost: 23 (THA→A→B→THA: 10+8+5), Got: " + r3.cost + " → " + (r3.cost == 23 ? "PASS" : "FAIL"));
  }

  private static Result solve(String[][] flights) {
    Map<String, Map<String, Integer>> graph = new HashMap<>();
    List<String> countries = new ArrayList<>();

    for (String[] flight : flights) {
      String from = flight[0];
      String to = flight[1];
      int cost = Integer.parseInt(flight[2]);

      graph.computeIfAbsent(from, k -> new HashMap<>()).put(to, cost);
      graph.computeIfAbsent(to, k -> new HashMap<>()).put(from, cost);

      if (!from.equals(START) && !countries.contains(from)) {
        countries.add(from);
      }
      if (!to.equals(START) && !countries.contains(to)) {
        countries.add(to);
      }
    }

    List<String> bestPath = new ArrayList<>();
    int[] maxCost = { Integer.MIN_VALUE };
    permute(countries, 0, graph, bestPath, maxCost);

    StringBuilder sb = new StringBuilder();
    sb.append(START);
    for (String country : bestPath) {
      sb.append(" ").append(country);
    }
    sb.append(" ").append(START);

    return new Result(sb.toString(), maxCost[0]);
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

    Map<String, Map<String, Integer>> graph = new HashMap<>();
    List<String> countries = new ArrayList<>();

    for (int i = 0; i < p; i++) {
      String from = sc.next();
      String to = sc.next();
      int cost = sc.nextInt();

      graph.computeIfAbsent(from, k -> new HashMap<>()).put(to, cost);
      graph.computeIfAbsent(to, k -> new HashMap<>()).put(from, cost);

      if (!from.equals(START) && !countries.contains(from)) {
        countries.add(from);
      }
      if (!to.equals(START) && !countries.contains(to)) {
        countries.add(to);
      }
    }
    sc.close();

    List<String> bestPath = new ArrayList<>();
    int[] maxCost = { Integer.MIN_VALUE };

    permute(countries, 0, graph, bestPath, maxCost);

    StringBuilder sb = new StringBuilder();
    sb.append(START);
    for (String country : bestPath) {
      sb.append(" ").append(country);
    }
    sb.append(" ").append(START);

    System.out.println(sb);
    System.out.println(maxCost[0]);
  }

  private static void permute(List<String> countries, int idx,
      Map<String, Map<String, Integer>> graph,
      List<String> bestPath, int[] maxCost) {
    if (idx == countries.size()) {
      int cost = calculateCost(countries, graph);
      if (cost > maxCost[0]) {
        maxCost[0] = cost;
        bestPath.clear();
        bestPath.addAll(countries);
      }
      return;
    }

    for (int i = idx; i < countries.size(); i++) {
      swap(countries, idx, i);
      permute(countries, idx + 1, graph, bestPath, maxCost);
      swap(countries, idx, i);
    }
  }

  private static void swap(List<String> list, int i, int j) {
    String temp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, temp);
  }

  private static int calculateCost(List<String> path, Map<String, Map<String, Integer>> graph) {
    int cost = 0;

    Integer startCost = getEdgeCost(graph, START, path.get(0));
    if (startCost == null)
      return Integer.MIN_VALUE;
    cost += startCost;

    for (int i = 0; i < path.size() - 1; i++) {
      Integer edgeCost = getEdgeCost(graph, path.get(i), path.get(i + 1));
      if (edgeCost == null)
        return Integer.MIN_VALUE;
      cost += edgeCost;
    }

    Integer endCost = getEdgeCost(graph, path.get(path.size() - 1), START);
    if (endCost == null)
      return Integer.MIN_VALUE;
    cost += endCost;

    return cost;
  }

  private static Integer getEdgeCost(Map<String, Map<String, Integer>> graph, String from, String to) {
    Map<String, Integer> edges = graph.get(from);
    if (edges == null)
      return null;
    return edges.get(to);
  }
}
