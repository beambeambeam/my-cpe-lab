package kmutt.cpe.algorithm.placeholder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class P01_Wee {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    sc.close();

    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();
    int sumLeft = 0;
    int sumRight = 0;

    for (int coin = n; coin >= 1; coin--) {
      if (sumLeft <= sumRight) {
        left.add(coin);
        sumLeft += coin;
      } else {
        right.add(coin);
        sumRight += coin;
      }
    }

    if (!left.contains(1)) {
      List<Integer> temp = left;
      left = right;
      right = temp;
    }

    Collections.sort(left);
    Collections.sort(right);

    StringBuilder sb1 = new StringBuilder();
    for (int i = 0; i < left.size(); i++) {
      if (i > 0)
        sb1.append(" ");
      sb1.append(left.get(i));
    }

    StringBuilder sb2 = new StringBuilder();
    for (int i = 0; i < right.size(); i++) {
      if (i > 0)
        sb2.append(" ");
      sb2.append(right.get(i));
    }

    System.out.println(sb1);
    System.out.println(sb2);
  }
}
