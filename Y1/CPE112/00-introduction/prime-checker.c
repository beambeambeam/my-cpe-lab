#include <math.h>
#include <stdio.h>

#define REP(i, a, b) for (int i = a; i <= b; i++)

int isPrime(int number) {
  if (number <= 1)
    return 0;

  REP(i, 2, sqrt(number)) {
    if ((number % i) == 0) {
      return 0;
    }
  }

  return 1;
}

int main(int argc, char const *argv[]) {
  int x, y, have_prime = 0;
  scanf("%d %d", &x, &y);

  REP(i, x, y) {
    if (isPrime(i) == 1) {
      printf("%d ", i);
      have_prime++;
    }
  }

  if (!have_prime)
    printf("none");

  return 0;
}
