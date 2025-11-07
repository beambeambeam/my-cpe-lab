package th.ac.kmutt.cpe.algorithm.supawit.lab.lab09;

import java.util.*;

public class HuffmanCoding {

  public static class HuffmanNode {
    char symbol;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char symbol, int frequency) {
      this.symbol = symbol;
      this.frequency = frequency;
      this.left = null;
      this.right = null;
    }

    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
      this.symbol = '\0';
      this.frequency = frequency;
      this.left = left;
      this.right = right;
    }

    public boolean isLeaf() {
      return left == null && right == null;
    }
  }

  public static class HuffmanTree {
    private HuffmanNode root;
    private Map<Character, String> codeMap;

    public HuffmanTree(HuffmanNode root) {
      this.root = root;
      this.codeMap = new HashMap<>();
      buildCodeMap(root, "");
    }

    public HuffmanNode getRoot() {
      return root;
    }

    public Map<Character, String> getCodeMap() {
      return codeMap;
    }

    private void buildCodeMap(HuffmanNode node, String code) {
      if (node == null) {
        return;
      }

      if (node.isLeaf()) {
        if (code.isEmpty()) {
          code = "0";
        }
        codeMap.put(node.symbol, code);
      } else {
        buildCodeMap(node.left, code + "0");
        buildCodeMap(node.right, code + "1");
      }
    }
  }

  public static HuffmanTree buildTree(String data) {
    if (data == null || data.isEmpty()) {
      throw new IllegalArgumentException("Input data cannot be null or empty");
    }

    Map<Character, Integer> frequencyMap = new HashMap<>();
    for (char c : data.toCharArray()) {
      frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
    }

    if (frequencyMap.size() == 1) {
      char onlyChar = frequencyMap.keySet().iterator().next();
      HuffmanNode root = new HuffmanNode(onlyChar, frequencyMap.get(onlyChar));
      return new HuffmanTree(root);
    }

    if (frequencyMap.size() == 2) {
      List<Map.Entry<Character, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());
      entries.sort((a, b) -> {
        if (a.getValue() != b.getValue()) {
          return Integer.compare(a.getValue(), b.getValue());
        }
        return Character.compare(a.getKey(), b.getKey());
      });

      HuffmanNode lowerFreq = new HuffmanNode(entries.get(0).getKey(), entries.get(0).getValue());
      HuffmanNode higherFreq = new HuffmanNode(entries.get(1).getKey(), entries.get(1).getValue());

      HuffmanNode intermediate = new HuffmanNode(higherFreq.frequency, null, higherFreq);
      HuffmanNode root = new HuffmanNode(lowerFreq.frequency + higherFreq.frequency, intermediate, lowerFreq);
      return new HuffmanTree(root);
    }

    PriorityQueue<HuffmanNode> pq = new PriorityQueue<>((a, b) -> {
      if (a.frequency != b.frequency) {
        return Integer.compare(a.frequency, b.frequency);
      }
      return Character.compare(a.symbol, b.symbol);
    });

    for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
      pq.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
    }

    while (pq.size() > 1) {
      HuffmanNode first = pq.poll();
      HuffmanNode second = pq.poll();

      int combinedFreq = first.frequency + second.frequency;
      HuffmanNode parent = new HuffmanNode(combinedFreq, second, first);
      pq.offer(parent);
    }

    HuffmanNode root = pq.poll();
    return new HuffmanTree(root);
  }

  public static String encode(String data, HuffmanTree tree) {
    if (data == null || data.isEmpty()) {
      return "";
    }

    Map<Character, String> codeMap = tree.getCodeMap();
    StringBuilder encoded = new StringBuilder();

    for (char c : data.toCharArray()) {
      String code = codeMap.get(c);
      if (code == null) {
        throw new IllegalArgumentException("Character '" + c + "' not found in Huffman tree");
      }
      encoded.append(code);
    }

    return encoded.toString();
  }

  public static String decode(String encodedData, HuffmanTree tree) {
    if (encodedData == null || encodedData.isEmpty()) {
      return "";
    }

    HuffmanNode root = tree.getRoot();
    if (root == null) {
      return "";
    }

    if (root.isLeaf()) {
      return String.valueOf(root.symbol).repeat(encodedData.length());
    }

    StringBuilder decoded = new StringBuilder();
    HuffmanNode current = root;

    for (char bit : encodedData.toCharArray()) {
      if (bit == '0') {
        current = current.left;
      } else if (bit == '1') {
        current = current.right;
      } else {
        throw new IllegalArgumentException("Invalid bit character: " + bit);
      }

      if (current == null) {
        throw new IllegalArgumentException("Invalid encoded data: path leads to null node");
      }

      if (current.isLeaf()) {
        decoded.append(current.symbol);
        current = root;
      }
    }

    if (current != root) {
      throw new IllegalArgumentException("Invalid encoded data: incomplete path at end");
    }

    return decoded.toString();
  }

  public static void Scanner() {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();

    HuffmanTree tree = buildTree(input);

    System.out.println("\nHuffman Code Map:");
    tree.getCodeMap().forEach((character, code) -> System.out.println("  '" + character + "' -> " + code));

    String encoded = encode(input, tree);
    System.out.println("\nEncoded: " + encoded);

    String decoded = decode(encoded, tree);
    System.out.println("Decoded: " + decoded);

    System.out.println("\nOriginal length: " + input.length() * 8 + " bits");
    System.out.println("Encoded length: " + encoded.length() + " bits");
    System.out.println("Compression ratio: " +
        String.format("%.2f%%", (1.0 - (double) encoded.length() / (input.length() * 8)) * 100));

    scanner.close();

  }
}
