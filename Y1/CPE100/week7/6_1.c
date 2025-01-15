// 67070501045 Supawit Marayat
#include <stdio.h>

long int factorial(int n) {
  int i;
  long int product = 1;
  for (i = n; i > 1; i--)
    product *= i;
  return product;
}

long int combination(int n, int r) {
  long int result;
  result = factorial(n) / (factorial(r) * factorial(n - r));
  return result;
}

int main() {
  int n, r;
  int ans;
  printf("To calculate nCr, enter n and r: ");
  scanf("%d %d", &n, &r);
  ans = combination(n, r);
  printf("\n%dC%d = %d", n, r, ans);
  return 0;
}
