#include <stdio.h>

void fibonacci(int n) {
  if (n == 0)
    printf("0");
  int fiboarray[500];
  fiboarray[0] = 1;
  fiboarray[1] = 1;
  for (int i = 2; i < n; i++) {
    fiboarray[i] = fiboarray[i - 1] + fiboarray[i - 2];
  }
  for (int j = 0; j < (n - 1); j++) {
    printf("%d ", fiboarray[j]);
  }
  printf("%d", fiboarray[n - 1]);
}

int main() {
  int n;
  scanf("%d", &n);
  fibonacci(n);

  return 0;
}
