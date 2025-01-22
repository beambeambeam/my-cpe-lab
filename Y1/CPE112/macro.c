#include <stdio.h>

#define REP(i, a, b) for (int i = a; i <= b; i++)

typedef struct {
  int *items;
  size_t count;
  size_t capacity;
} Numbers;
