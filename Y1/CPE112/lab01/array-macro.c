#include <stdio.h>
#include <stdlib.h>

typedef struct {
  int *items;
  size_t count;
  size_t capacity;
} Numbers;

void resize(Numbers *xs, int new_capacity) {
  if (new_capacity < xs->count) {
    xs->count = new_capacity;
  }
  xs->items = realloc(xs->items, new_capacity * sizeof(*xs->items));
  xs->capacity = new_capacity;
}

void append(Numbers *xs, int x) {
  if (xs->count >= xs->capacity) {
    if (xs->capacity == 0) {
      xs->capacity = 64;
    } else {
      xs->capacity *= 2;
    }
    resize(xs, xs->capacity);
  }

  xs->items[xs->count++] = x;
}

void remove_at(Numbers *xs, size_t index) {
  if (index >= xs->count) {
    return;
  }
  for (size_t i = index; i < xs->count - 1; ++i) {
    xs->items[i] = xs->items[i + 1];
  }
  xs->count--;
}

int main(void) {
  Numbers xs = {0};
  for (int x = 0; x < 10; ++x)
    append(&xs, x);
  remove_at(&xs, 5);
  for (size_t i = 0; i < xs.count; ++i)
    printf("%d\n", xs.items[i]);
  return 0;
}
