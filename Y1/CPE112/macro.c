#include <stdio.h>

#define REP(i, a, b) for (int i = a; i <= b; i++)

typedef struct {
  int *items;
  size_t count;
  size_t capacity;
} Array;

int main(int argc, char const *argv[]) {
  Array Numbers = {};
  Numbers.count = 0;
  Numbers.capacity = 10;
  Numbers.items = (int *)malloc(Numbers.capacity * sizeof(int));
  if (Numbers.items == NULL) {
    fprintf(stderr, "Memory allocation failed\n");
    return 1;
  }

  REP(i, 0, 9) {
    Numbers.items[i] = i + 1;
    Numbers.count++;
  }

  REP(i, 0, Numbers.count - 1) {
    printf("%d ", Numbers.items[i]);
  }
  printf("\n");

  free(Numbers.items);
  return 0;
}
