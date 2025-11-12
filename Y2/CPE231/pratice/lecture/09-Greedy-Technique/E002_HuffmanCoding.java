import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class E002_HuffmanCoding {

    public static GraphUtils.WeightedBinaryTreeNode<Character> buildHuffmanTree(
        Map<Character, Integer> frequencies
    ) {
        PriorityQueue<GraphUtils.WeightedBinaryTreeNode<Character>> pq =
            new PriorityQueue<>();

        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            pq.offer(
                new GraphUtils.WeightedBinaryTreeNode<>(
                    entry.getKey(),
                    entry.getValue()
                )
            );
        }

        while (pq.size() > 1) {
            GraphUtils.WeightedBinaryTreeNode<Character> left = pq.poll();
            GraphUtils.WeightedBinaryTreeNode<Character> right = pq.poll();
            int combinedFreq = left.weight + right.weight;
            GraphUtils.WeightedBinaryTreeNode<Character> parent =
                new GraphUtils.WeightedBinaryTreeNode<>(
                    combinedFreq,
                    left,
                    right
                );
            pq.offer(parent);
        }

        return pq.poll();
    }

    public static void generateCodes(
        GraphUtils.WeightedBinaryTreeNode<Character> root,
        String code,
        Map<Character, String> codes
    ) {
        if (root == null) {
            return;
        }

        if (root.isLeaf()) {
            if (code.isEmpty()) {
                codes.put(root.data, "0");
            } else {
                codes.put(root.data, code);
            }
            return;
        }

        generateCodes(root.left, code + "0", codes);
        generateCodes(root.right, code + "1", codes);
    }

    public static Map<Character, String> getHuffmanCodes(
        Map<Character, Integer> frequencies
    ) {
        GraphUtils.WeightedBinaryTreeNode<Character> root = buildHuffmanTree(
            frequencies
        );
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

    public static String decode(
        String encoded,
        GraphUtils.WeightedBinaryTreeNode<Character> root
    ) {
        if (root == null) {
            return "";
        }

        StringBuilder decoded = new StringBuilder();
        GraphUtils.WeightedBinaryTreeNode<Character> current = root;

        for (char bit : encoded.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }

            if (current.isLeaf()) {
                decoded.append(current.data);
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
        System.out.println(
            "Compression ratio: " +
                String.format(
                    "%.2f%%",
                    (1.0 - (double) encoded.length() / (text.length() * 8)) *
                        100
                )
        );

        GraphUtils.WeightedBinaryTreeNode<Character> root = buildHuffmanTree(
            frequencies
        );
        String decoded = decode(encoded, root);
        System.out.println("Decoded text: " + decoded);

        scanner.close();
    }
}
