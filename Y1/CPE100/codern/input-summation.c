#include <stdio.h>

int main() {
  int input, sum = 0;
  int count = 0;

  do {
    printf("Enter input %d (type -1 to end this program): ", count + 1);
    scanf("%d", &input);

    if (input != -1) {
      sum += input;
      count++;
    }
  } while (input != -1);

  printf("The summation of %d inputs is %d.\n", count, sum);

  return 0;
}
