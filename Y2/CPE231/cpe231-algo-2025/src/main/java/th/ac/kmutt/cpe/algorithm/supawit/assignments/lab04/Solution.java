package th.ac.kmutt.cpe.algorithm.supawit.lab.lab04;

import java.util.Scanner;

public class Solution {
  public int countValidSubstrings(String beads, char A, char B, int p) {
    int count = 0;
    int n = beads.length();

    for (int i = 0; i < n; i++) {
      if (beads.charAt(i) == A) {
        if (A == B && p == 1) {
          count++;
        }

        for (int j = i + 1; j < n; j++) {
          if (beads.charAt(j) == B) {
            int length = j - i + 1;
            if (length >= p) {
              count++;
            }
          }
        }
      }
    }

    return count;
  }

  public void scanner() {
    Scanner scanner = new Scanner(System.in);

    String beads = scanner.nextLine().trim();
    String[] chars = scanner.nextLine().trim().split(" ");
    char A = chars[0].charAt(0);
    char B = chars[1].charAt(0);
    int p = Integer.parseInt(scanner.nextLine().trim());

    int result = countValidSubstrings(beads, A, B, p);
    System.out.println(result);

    scanner.close();
  }
}
