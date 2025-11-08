import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class E002_HuffmanCoding {
  static class HuffmanNode implements Comparable<HuffmanNode> {
    char character;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    HuffmanNode(char character, int frequency) {
      this.character = character;
      this.frequency = frequency;
      this.left = null;
      this.right = null;
    }

    HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
      this.character = '\0';
      this.frequency = frequency;
      this.left = left;
      this.right = right;
    }

    boolean isLeaf() {
      return left == null && right == null;
    }

    @Override
    public int compareTo(HuffmanNode other) {
      return Integer.compare(this.frequency, other.frequency);
    }
  }

  public static HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencies) {
    PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();

    for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
      pq.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
    }

    while (pq.size() > 1) {
      HuffmanNode left = pq.poll();
      HuffmanNode right = pq.poll();
      int combinedFreq = left.frequency + right.frequency;
      HuffmanNode parent = new HuffmanNode(combinedFreq, left, right);
      pq.offer(parent);
    }

    return pq.poll();
  }

  public static void generateCodes(HuffmanNode root, String code, Map<Character, String> codes) {
    if (root == null) {
      return;
    }

    if (root.isLeaf()) {
      if (code.isEmpty()) {
        codes.put(root.character, "0");
      } else {
        codes.put(root.character, code);
      }
      return;
    }

    generateCodes(root.left, code + "0", codes);
    generateCodes(root.right, code + "1", codes);
  }

  public static Map<Character, String> getHuffmanCodes(Map<Character, Integer> frequencies) {
    HuffmanNode root = buildHuffmanTree(frequencies);
    Map<Character, String> codes = new HashMap<>();
    generateCodes(root, "", codes);
    return codes;
  }

  public static String encode(String text, Map<Character, String> codes) {
    StringBuilder encoded = new StringBuilder();
    for (char c : text.toCharArray()) {
      encoded.append(codes.get(c));
    }
    return encoded.toString();
  }

  public static String decode(String encoded, HuffmanNode root) {
    if (root == null) {
      return "";
    }

    StringBuilder decoded = new StringBuilder();
    HuffmanNode current = root;

    for (char bit : encoded.toCharArray()) {
      if (bit == '0') {
        current = current.left;
      } else {
        current = current.right;
      }

      if (current.isLeaf()) {
        decoded.append(current.character);
        current = root;
      }
    }

    return decoded.toString();
  }

  public static void main(String[] args) {
    java.util.Scanner scanner = new java.util.Scanner(System.in);

    System.out.print("Enter the text to encode: ");
    String text = scanner.nextLine();

    Map<Character, Integer> frequencies = new HashMap<>();
    for (char c : text.toCharArray()) {
      frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
    }

    System.out.println("\nCharacter frequencies:");
    for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
      System.out.println("'" + entry.getKey() + "': " + entry.getValue());
    }

    Map<Character, String> codes = getHuffmanCodes(frequencies);

    System.out.println("\nHuffman codes:");
    for (Map.Entry<Character, String> entry : codes.entrySet()) {
      System.out.println("'" + entry.getKey() + "': " + entry.getValue());
    }

    String encoded = encode(text, codes);
    System.out.println("\nEncoded text: " + encoded);
    System.out.println("Original size: " + (text.length() * 8) + " bits");
    System.out.println("Encoded size: " + encoded.length() + " bits");
    System.out.println("Compression ratio: " + String.format("%.2f%%",
        (1.0 - (double) encoded.length() / (text.length() * 8)) * 100));

    HuffmanNode root = buildHuffmanTree(frequencies);
    String decoded = decode(encoded, root);
    System.out.println("Decoded text: " + decoded);

    scanner.close();
  }
}
