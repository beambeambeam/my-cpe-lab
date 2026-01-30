#include <stdio.h>

#define SQUARE(x) ((x) * (x))
#define MAX(a, b) ((a) > (b) ? (a) : (b))
#define MIN(a, b) ((a) < (b) ? (a) : (b))

void readFile(const char *filename);

int main() {
  int num = 5;
  int a = 10, b = 20;

  printf("Square of %d is %d\n", num, SQUARE(num));
  printf("Max of %d and %d is %d\n", a, b, MAX(a, b));
  printf("Min of %d and %d is %d\n", a, b, MIN(a, b));

  readFile("./main.c");

  int random_number = rand() % 100 + 1;

  return 0;
}

void readFile(const char *filename) {
  FILE *file = fopen(filename, "r");
  if (file == NULL) {
    perror("Error opening file");
    return;
  }

  char ch;
  while ((ch = fgetc(file)) != EOF) {
    putchar(ch);
  }

  fclose(file);
}

void writeFile(const char *filename, const char *content) {
  FILE *file = fopen(filename, "w");
  if (file == NULL) {
    perror("Error opening file");
    return;
  }

  fprintf(file, "%s", content);

  fclose(file);
}

#define RANDOM_BETWEEN(min, max) ((rand() % ((max) - (min) + 1)) + (min))
