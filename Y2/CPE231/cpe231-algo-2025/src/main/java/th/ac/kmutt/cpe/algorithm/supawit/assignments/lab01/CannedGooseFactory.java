import java.util.Scanner;

public class CannedGooseFactory {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    long currentMushrooms = 0;
    long currentTomatoes = 0;
    long totalRegularCansProduced = 0;
    long totalSpecialCansProduced = 0;

    final int MUSHROOMS_PER_REGULAR = 5;
    final int TOMATOES_PER_REGULAR = 3;
    final int MUSHROOMS_PER_SPECIAL = 3;
    final int TOMATOES_PER_SPECIAL = 5;

    while (true) {
      int cmd = scanner.nextInt();

      if (cmd == 0) {
        break;
      }

      int a = scanner.nextInt();
      int b = scanner.nextInt();

      if (cmd == 1) {
        currentMushrooms += a;
        currentTomatoes += b;

      } else if (cmd == 2) {
        long neededMushrooms = (long) a * MUSHROOMS_PER_REGULAR + (long) b * MUSHROOMS_PER_SPECIAL;
        long neededTomatoes = (long) a * TOMATOES_PER_REGULAR + (long) b * TOMATOES_PER_SPECIAL;

        if (currentMushrooms >= neededMushrooms && currentTomatoes >= neededTomatoes) {
          System.out.println("yes");

          currentMushrooms -= neededMushrooms;
          currentTomatoes -= neededTomatoes;

          totalRegularCansProduced += a;
          totalSpecialCansProduced += b;
        } else {
          System.out.println("no");
        }
      }
    }

    System.out.println(
        totalRegularCansProduced + " " +
            totalSpecialCansProduced + " " +
            currentMushrooms + " " +
            currentTomatoes);

    scanner.close();
  }
}
