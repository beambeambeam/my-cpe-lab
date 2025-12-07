package kmutt.cpe.algorithm.placeholder;

import java.util.PriorityQueue;
import java.util.Scanner;

public class P04_DragQueen {

  public static void main(String[] args) {
    runTests();
    // runInteractive();
  }

  private static void runTests() {
    // Test 1: "aabca" → can be rearranged (e.g., "abaca")
    String result1 = rearrange("aabca");
    boolean valid1 = isValidArrangement(result1, "aabca");
    System.out.println("Test 1: input=\"aabca\"");
    System.out.println("Output: " + result1);
    System.out.println("Expected: valid arrangement (no adjacent same), Got: " + (valid1 ? "PASS" : "FAIL"));
    System.out.println();

    // Test 2: "aabaca" → IMPOSSIBLE (3 a's out of 6, need (6+1)/2=3.5, but 3 is not > 3)
    // Actually: "aabaca" has 4 a's, 1 b, 1 c → total 6. maxFreq=4 > (6+1)/2=3 → IMPOSSIBLE
    String result2 = rearrange("aabaca");
    System.out.println("Test 2: input=\"aabaca\"");
    System.out.println("Output: " + result2);
    System.out.println("Expected: IMPOSSIBLE, Got: " + (result2.equals("IMPOSSIBLE") ? "PASS" : "FAIL"));
    System.out.println();

    // Test 3: "aab" → can be rearranged to "aba"
    String result3 = rearrange("aab");
    boolean valid3 = isValidArrangement(result3, "aab");
    System.out.println("Test 3: input=\"aab\"");
    System.out.println("Output: " + result3);
    System.out.println("Expected: valid arrangement (e.g., \"aba\"), Got: " + (valid3 ? "PASS" : "FAIL"));
    System.out.println();

    // Test 4: "aaab" → IMPOSSIBLE (3 a's out of 4, (4+1)/2=2, 3>2)
    String result4 = rearrange("aaab");
    System.out.println("Test 4: input=\"aaab\"");
    System.out.println("Output: " + result4);
    System.out.println("Expected: IMPOSSIBLE, Got: " + (result4.equals("IMPOSSIBLE") ? "PASS" : "FAIL"));
    System.out.println();

    // Test 5: "abcdef" → already valid, no rearrangement needed
    String result5 = rearrange("abcdef");
    boolean valid5 = isValidArrangement(result5, "abcdef");
    System.out.println("Test 5: input=\"abcdef\"");
    System.out.println("Output: " + result5);
    System.out.println("Expected: valid arrangement, Got: " + (valid5 ? "PASS" : "FAIL"));
    System.out.println();

    // Test 6: "aaabbbccc" → can be rearranged (e.g., "abcabcabc")
    String result6 = rearrange("aaabbbccc");
    boolean valid6 = isValidArrangement(result6, "aaabbbccc");
    System.out.println("Test 6: input=\"aaabbbccc\"");
    System.out.println("Output: " + result6);
    System.out.println("Expected: valid arrangement, Got: " + (valid6 ? "PASS" : "FAIL"));
  }

  private static boolean isValidArrangement(String result, String original) {
    if (result.equals("IMPOSSIBLE")) {
      return false;
    }
    if (result.length() != original.length()) {
      return false;
    }
    // Check same character counts
    int[] freq1 = new int[26];
    int[] freq2 = new int[26];
    for (char c : original.toCharArray()) {
      freq1[c - 'a']++;
    }
    for (char c : result.toCharArray()) {
      freq2[c - 'a']++;
    }
    for (int i = 0; i < 26; i++) {
      if (freq1[i] != freq2[i]) {
        return false;
      }
    }
    // Check no adjacent same
    for (int i = 1; i < result.length(); i++) {
      if (result.charAt(i) == result.charAt(i - 1)) {
        return false;
      }
    }
    return true;
  }

  @SuppressWarnings("unused")
  private static void runInteractive() {
    Scanner sc = new Scanner(System.in);
    String queue = sc.nextLine();
    sc.close();

    String result = rearrange(queue);
    System.out.println(result);
  }

  private static String rearrange(String s) {
    int[] freq = new int[26];
    for (char c : s.toCharArray()) {
      freq[c - 'a']++;
    }

    int maxFreq = 0;
    for (int f : freq) {
      maxFreq = Math.max(maxFreq, f);
    }

    if (maxFreq > (s.length() + 1) / 2) {
      return "IMPOSSIBLE";
    }

    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
    for (int i = 0; i < 26; i++) {
      if (freq[i] > 0) {
        pq.offer(new int[] { i, freq[i] });
      }
    }

    StringBuilder result = new StringBuilder();
    int[] prev = null;

    while (!pq.isEmpty()) {
      int[] curr = pq.poll();
      result.append((char) (curr[0] + 'a'));
      curr[1]--;

      if (prev != null && prev[1] > 0) {
        pq.offer(prev);
      }

      prev = curr;
    }

    return result.toString();
  }
}
