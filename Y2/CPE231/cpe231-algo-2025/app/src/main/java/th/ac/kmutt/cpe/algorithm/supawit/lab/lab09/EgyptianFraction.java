package th.ac.kmutt.cpe.algorithm.supawit.lab.lab09;

import java.util.*;

public class EgyptianFraction {
  public static void printFraction(int nr, int dr) {
    if (dr == 0 || nr == 0) {
      return;
    }

    if (dr % nr == 0) {
      System.out.print("1/" + dr / nr);
      return;
    }

    if (nr % dr == 0) {
      System.out.print(nr / dr);
      return;
    }

    if (nr > dr) {
      System.out.print(nr / dr + " + ");
      printFraction(nr % dr, dr);
      return;
    }

    int n = dr / nr + 1;
    System.out.print("1/" + n + " + ");

    printFraction(nr * n - dr, dr * n);
  }

  public static void Scanner() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter numerator: ");
    int nr = scanner.nextInt();
    System.out.print("Enter denominator: ");
    int dr = scanner.nextInt();
    System.out.print("Egyptian Fraction representation of " + nr + "/" + dr + " is: ");
    printFraction(nr, dr);
    System.out.println();
    scanner.close();

  }

}
