#include <stdio.h>
#include <stdlib.h>

typedef struct {
  int *items;
  size_t count;
  size_t capacity;
} Numbers;

int main(int argc, char const *argv[]) {
  int size;
  scanf("%d", &size);

  Numbers arr;

  arr.count = 0;
  arr.capacity = size;
  arr.items = (int *)malloc(arr.capacity * sizeof(int));

  for (int i = 0; i < size; i++) {
    scanf("%d", (arr.items + i));
    arr.count++;
  }

  int fx;
  scanf("%d", &fx);

  if (fx == 1) {
    if (size < 2) {
      printf("none");
    }

    for (size_t i = 0; i < arr.count; i++) {
      if (i % 2 != 0) {
        printf("%d ", *(arr.items + i));
      }
    }

  } else {
    for (size_t i = 0; i < arr.count; i++) {
      if (i % 2 == 0) {
        printf("%d ", *(arr.items + i));
      }
    }
  }

  return 0;
}
