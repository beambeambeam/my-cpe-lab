import java.util.Scanner;

public class P07_MakeAWish {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }

    int result = findMaxSum(arr, arr.length);
    System.out.println(result);
    sc.close();
  }

  private static int findMaxSum(int[] arr, int length) {
    int best_sum = Integer.MIN_VALUE;
    int currentSum = 0;
    for (int i : arr) {
      currentSum = Math.max(i, currentSum + i);
      best_sum = Math.max(currentSum, best_sum);
    }
    return best_sum;
  }
}
