
public class Main {
  public static void main(String[] args) {
    graph1();
    graph2();
    graph3();
    graph4();
  }

  public static void graph1() {
    UndirectedGraph graph = new UndirectedGraph(false);

    graph.addEdge("A", "B");
    graph.addEdge("A", "D");
    graph.addEdge("B", "C");
    graph.addEdge("B", "D");
    graph.addEdge("C", "F");
    graph.addEdge("C", "E");
    graph.addEdge("C", "D");

    System.out.println("A // output is " + graph.getOutDegree("A"));
    System.out.println("B // output is " + graph.getOutDegree("C"));
  }

  public static void graph2() {
    DirectedGraph graph = new DirectedGraph(false);

    graph.addEdge("A", "B");
    graph.addEdge("A", "E");
    graph.addEdge("B", "D");
    graph.addEdge("B", "F");
    graph.addEdge("D", "A");
    graph.addEdge("E", "A");
    graph.addEdge("F", "A");
    graph.addEdge("F", "G");

    System.out.println("E // output is " + graph.getOutDegree("E"));
    System.out.println("A // output is " + graph.getOutDegree("A"));
    System.out.println("G // output is " + graph.getOutDegree("G"));
  }

  public static void graph3() {
    UndirectedGraph graph = new UndirectedGraph(true);

    graph.addEdge("A", "E", 3);
    graph.addEdge("A", "G", 23);
    graph.addEdge("B", "E", 2);
    graph.addEdge("B", "H", 10);
    graph.addEdge("B", "D", 5);
    graph.addEdge("C", "F", 14);
    graph.addEdge("D", "H", 9);
    graph.addEdge("E", "G", 2);
    graph.addEdge("H", "B", 14);

    System.out.println("E // output is " + graph.getOutDegree("E"));
    System.out.println("H // output is " + graph.getOutDegree("H"));
    System.out.println("F // output is " + graph.getOutDegree("F"));
  }

  public static void graph4() {
    DirectedGraph graph = new DirectedGraph(true);

    graph.addEdge("A", "H", 10);
    graph.addEdge("A", "J", 5);
    graph.addEdge("A", "C", 3);
    graph.addEdge("B", "H", 9);
    graph.addEdge("C", "F", 14);
    graph.addEdge("E", "A", 6);
    graph.addEdge("G", "E", 1);
    graph.addEdge("G", "F", 3);
    graph.addEdge("H", "D", 14);

    System.out.println("A // output is " + graph.getOutDegree("A"));
    System.out.println("J // output is " + graph.getOutDegree("J"));
    System.out.println("C // output is " + graph.getOutDegree("C"));
    System.out.println("H // output is " + graph.getOutDegree("H"));
  }
}
