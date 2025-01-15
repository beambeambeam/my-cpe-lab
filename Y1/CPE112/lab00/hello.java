import java.util.Scanner;

public class hello {

  public static void main(String[] args) {
    // System.out.println("hello world");

    // Scanner sc = new Scanner(System.in);

    // int n = sc.nextInt();

    // for (int i = 0; i < n; i++) {
    // printName(sc.next());
    // }

    // sc.close();

    int[] arr = { 1, 2, 3 };

    for (int i : arr) {
      System.out.println(i);
    }
  }

  static void printName(String name) {
    System.out.printf("my name is %s \n", name);
  }
}
