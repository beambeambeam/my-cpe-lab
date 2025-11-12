public class E001_ChangeMaking {

    public static int changeMaking(int[] D, int n) {
        int count = 0;
        int remaining = n;
        int m = D.length;

        for (int i = m - 1; i >= 0; i--) {
            if (remaining >= D[i]) {
                int coins = remaining / D[i];
                count += coins;
                remaining = remaining % D[i];
            }
            if (remaining == 0) {
                break;
            }
        }

        if (remaining != 0) {
            return -1;
        }

        return count;
    }

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.print("Enter the number of coin denominations: ");
        int m = scanner.nextInt();

        int[] D = new int[m];
        System.out.print(
            "Enter coin denominations (must be sorted in ascending order): "
        );
        for (int i = 0; i < m; i++) {
            D[i] = scanner.nextInt();
        }

        System.out.print("Enter the target amount: ");
        int n = scanner.nextInt();

        int result = changeMaking(D, n);

        if (result == -1) {
            System.out.println("Cannot make change with given denominations.");
        } else {
            System.out.println("Number of coins needed: " + result);
        }

        scanner.close();
    }
}
