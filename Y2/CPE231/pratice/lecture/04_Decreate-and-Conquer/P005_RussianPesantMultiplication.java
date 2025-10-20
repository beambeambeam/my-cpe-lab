public class P005_RussianPesantMultiplication {

  private static int multiply(int n, int m) {
    int result = 0;
    while (n > 0) {
      if (n % 2 != 0)
        result += m;
      n /= 2;
      m *= 2;
    }
    return result;
  }

  public static void main(String[] args) {
    int n = 47;
    int m = 42;
    int result = multiply(n, m);
    System.out.println(result);
  }
}
