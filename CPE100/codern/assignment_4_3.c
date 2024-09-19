#include <math.h>
#include <stdio.h>

int main() {
  int scores[100];
  int count = 0;
  float sum = 0.0, mean, standard_deviation = 0.0;

  printf("Input student scores (type -1 to start calculation)\n");

  for (int i = 0; i < 100; i++) {
    printf("Enter score of student %d: ", i + 1);
    scanf("%d", &scores[i]);
    if (scores[i] == -1) {
      break;
    }
    sum += scores[i];
    count++;
  }

  mean = sum / count;

  for (int i = 0; i < count; i++) {
    standard_deviation += pow(scores[i] - mean, 2);
  }

  standard_deviation = sqrt(standard_deviation / count);

  printf("Mean score of %d students = %.1f, SD = %.2f", count, mean,
         standard_deviation);

  return 0;
}
