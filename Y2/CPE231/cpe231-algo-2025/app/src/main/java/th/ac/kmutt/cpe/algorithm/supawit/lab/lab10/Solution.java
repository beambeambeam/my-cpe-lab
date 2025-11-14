package th.ac.kmutt.cpe.algorithm.supawit.lab.lab10;

import java.util.*;

public class Solution {

  public int[] solve(int n, int[][] companyPreferences, int[][] studentPreferences) {
    int[] studentMatch = new int[n];
    int[] companyMatch = new int[n];
    int[] nextProposal = new int[n];

    Arrays.fill(studentMatch, -1);
    Arrays.fill(companyMatch, -1);
    Arrays.fill(nextProposal, 0);

    int[][] studentRank = new int[n][n];
    for (int student = 0; student < n; student++) {
      for (int rank = 0; rank < n; rank++) {
        int company = studentPreferences[student][rank];
        studentRank[student][company] = rank;
      }
    }

    Queue<Integer> freeCompanies = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      freeCompanies.offer(i);
    }

    while (!freeCompanies.isEmpty()) {
      int company = freeCompanies.poll();

      if (nextProposal[company] >= n) {
        continue;
      }

      int student = companyPreferences[company][nextProposal[company]];
      nextProposal[company]++;

      if (studentMatch[student] == -1) {
        studentMatch[student] = company;
        companyMatch[company] = student;
      } else {
        int currentCompany = studentMatch[student];
        if (studentRank[student][company] < studentRank[student][currentCompany]) {
          studentMatch[student] = company;
          companyMatch[company] = student;
          companyMatch[currentCompany] = -1;
          freeCompanies.offer(currentCompany);
        } else {
          freeCompanies.offer(company);
        }
      }
    }

    return studentMatch;
  }

  public void scanner() {
    Scanner scanner = new Scanner(System.in);

    int n = scanner.nextInt();

    int[][] companyPreferences = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        companyPreferences[i][j] = scanner.nextInt();
      }
    }

    int[][] studentPreferences = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        studentPreferences[i][j] = scanner.nextInt();
      }
    }

    scanner.close();

    int[] result = solve(n, companyPreferences, studentPreferences);

    for (int student = 0; student < n; student++) {
      System.out.println(student + " " + result[student]);
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    solution.scanner();
  }
}
