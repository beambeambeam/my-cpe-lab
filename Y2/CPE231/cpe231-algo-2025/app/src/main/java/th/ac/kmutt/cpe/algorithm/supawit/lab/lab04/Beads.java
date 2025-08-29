package th.ac.kmutt.cpe.algorithm.supawit.lab.lab04;

import java.util.Scanner;

public class Beads {
  public static int countValidSubstrings(String beads, char A, char B, int p) {
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

  public static void solution() {
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

  public static void runTests() {
    System.out.println("Running test cases...");

    // Test case 1
    String beads1 = "CABAAXBYA";
    char A1 = 'A', B1 = 'B';
    int p1 = 3;
    int expected1 = 3;
    int result1 = countValidSubstrings(beads1, A1, B1, p1);
    System.out.println("Test 1: " + (result1 == expected1 ? "PASS" : "FAIL") +
        " (Expected: " + expected1 + ", Got: " + result1 + ")");

    // Test case 2
    String beads2 = "TAWEECHAINUNTAWISUTTIWONGCPEKMUTT";
    char A2 = 'E', B2 = 'T';
    int p2 = 5;
    int expected2 = 12;
    int result2 = countValidSubstrings(beads2, A2, B2, p2);
    System.out.println("Test 2: " + (result2 == expected2 ? "PASS" : "FAIL") +
        " (Expected: " + expected2 + ", Got: " + result2 + ")");

    // Test case 3
    String beads3 = "IAMVERYHANDSOMEANDBEAUTIFULANDVERYGOODATCODING";
    char A3 = 'S', B3 = 'G';
    int p3 = 10;
    int expected3 = 2;
    int result3 = countValidSubstrings(beads3, A3, B3, p3);
    System.out.println("Test 3: " + (result3 == expected3 ? "PASS" : "FAIL") +
        " (Expected: " + expected3 + ", Got: " + result3 + ")");

    // Test case 4
    String beads4 = "AAEWA";
    char A4 = 'A', B4 = 'A';
    int p4 = 1;
    int expected4 = 6;
    int result4 = countValidSubstrings(beads4, A4, B4, p4);
    System.out.println("Test 4: " + (result4 == expected4 ? "PASS" : "FAIL") +
        " (Expected: " + expected4 + ", Got: " + result4 + ")");

  }

}
