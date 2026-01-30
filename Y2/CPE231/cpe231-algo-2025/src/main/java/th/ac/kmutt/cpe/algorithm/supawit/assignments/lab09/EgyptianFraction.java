package th.ac.kmutt.cpe.algorithm.supawit.lab.lab09;

import java.util.*;

public class EgyptianFraction {

  private static int gcd(int a, int b) {
    if (b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }

  public static void printFraction(int nr, int dr) {
    if (dr == 0 || nr == 0) {
      return;
    }

    int g = gcd(nr, dr);
    nr = nr / g;
    dr = dr / g;

    if (nr == 1) {
      System.out.print("1/" + dr);
      return;
    }

    if (nr > dr) {
      System.out.print(nr / dr + " + ");
      printFraction(nr % dr, dr);
      return;
    }

    int n = (dr + nr - 1) / nr;
    System.out.print("1/" + n + " + ");

    int newNr = nr * n - dr;
    int newDr = dr * n;
    printFraction(newNr, newDr);
  }

  public static void Scanner() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter numerator: ");
    int nr = scanner.nextInt();
    System.out.print("Enter denominator: ");
    int dr = scanner.nextInt();

    if (dr == 0) {
      System.out.println("Error: Denominator cannot be zero.");
      scanner.close();
      return;
    }

    if (nr < 0 || dr < 0) {
      System.out.println("Error: Numerator and denominator must be positive.");
      scanner.close();
      return;
    }

    System.out.print(
        "Egyptian Fraction representation of " + nr + "/" + dr + " is: ");
    printFraction(nr, dr);
    System.out.println();
    scanner.close();
  }
}
