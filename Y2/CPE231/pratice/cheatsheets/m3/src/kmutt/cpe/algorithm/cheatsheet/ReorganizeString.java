package kmutt.cpe.algorithm.cheatsheet;

// javac kmutt\cpe\algorithm\cheatsheet\ReorganizeString.java
// java kmutt.cpe.algorithm.cheatsheet.ReorganizeString

import java.util.PriorityQueue;

// Reorganize String: rearrange so no two adjacent chars are same
// Greedy: always pick char with highest remaining freq (that isn't previous)
// Impossible if any char freq > (n+1)/2
// Uses max-heap to track (char, freq), hold prev char until next iteration
// Time: O(n log k) where k = unique chars, Space: O(k)

public class ReorganizeString {

  public static String solve(String s) {
    int[] freq = new int[26];
    for (char c : s.toCharArray()) freq[c - 'a']++;

    // Check if possible
    int maxFreq = 0;
    for (int f : freq) maxFreq = Math.max(maxFreq, f);
    if (maxFreq > (s.length() + 1) / 2) return "IMPOSSIBLE";

    // Max-heap: [charIndex, frequency]
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
    for (int i = 0; i < 26; i++) {
      if (freq[i] > 0) pq.offer(new int[] { i, freq[i] });
    }

    StringBuilder result = new StringBuilder();
    int[] prev = null;

    while (!pq.isEmpty()) {
      int[] curr = pq.poll();        // get most frequent
      result.append((char) (curr[0] + 'a'));
      curr[1]--;

      if (prev != null && prev[1] > 0) pq.offer(prev); // re-add prev if still has count
      prev = curr;                   // hold current (can't use next iteration)
    }

    return result.toString();
  }

  public static void main(String[] args) {
    String[] tests = { "aabca", "aabaca", "aab", "aaab", "abcdef", "aaabbbccc" };
    for (String t : tests) {
      String res = solve(t);
      System.out.println(t + " -> " + res + " " + (isValid(res, t) ? "OK" : ""));
    }
  }

  static boolean isValid(String res, String orig) {
    if (res.equals("IMPOSSIBLE")) return true;
    if (res.length() != orig.length()) return false;
    for (int i = 1; i < res.length(); i++)
      if (res.charAt(i) == res.charAt(i - 1)) return false;
    return true;
  }
}
