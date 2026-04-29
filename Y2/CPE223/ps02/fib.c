#include <stdio.h>

int main() {
  long long a = 0, b = 1, temp;

  for (int i = 2; i <= 44; i++) {
    temp = a + b;
    a = b;
    b = temp;
  }

  printf("44th Fibonacci number: %lld\n", b);
  return 0;
}
