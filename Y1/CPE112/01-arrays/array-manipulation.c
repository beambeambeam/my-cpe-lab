#include <stdio.h>
#include <stdlib.h>

#define REP(i, a, b) for (int i = a; i <= b; i++)

typedef struct {
  int *items;
  size_t count;
  size_t capacity;
} Numbers;

void printArray(int *arr, int size) {
  REP(i, 0, size - 1) { printf("%d ", *(arr + i)); }
}
void insertArray(int *arr, int size, int index, int value) {
  if (index > size || index < 0) {
    printf("Index out of bounds");
  }

  for (int i = size; i > index; i--) {
    *(arr + i) = *(arr + i - 1);
  }

  *(arr + index) = value;
}
void deleteArray(int *arr, int size, int index) {
  if (index >= size || index < 0) {
    printf("Index out of bounds");
    return;
  }

  for (int i = index; i < size - 1; i++) {
    *(arr + i) = *(arr + i + 1);
  }
  *(arr + size - 1) = 0;
}
void mergeArray(int *arr1, int size1, int *arr2, int size2) {
  Numbers mergeArr;
  mergeArr.count = 0;
  mergeArr.capacity = size1 + size2;
  mergeArr.items = (int *)malloc(mergeArr.capacity * sizeof(int));

  REP(i, 0, mergeArr.capacity - 1) {
    if (i < size1) {
      *(mergeArr.items + i) = *(arr1 + i);
    } else {
      *(mergeArr.items + i) = *(arr2 + (i - size1));
    }
    mergeArr.count++;
  }

  printArray(mergeArr.items, mergeArr.count);
  free(mergeArr.items);
}

int main(int argc, char const *argv[]) {
  int size, size2;
  Numbers arr1, arr2;

  // 1st arr opeation only
  int insert_index, insert_item, remove_index;

  scanf("%d", &size);

  arr1.count = 0;
  arr1.capacity = size;
  arr1.items = (int *)malloc(arr1.capacity / sizeof(int));

  REP(i, 0, size - 1) {
    scanf("%d", (arr1.items + i));
    arr1.count++;
  }

  scanf("%d", &size2);

  arr2.count = 0;
  arr2.capacity = size2;
  arr2.items = (int *)malloc(arr2.capacity / sizeof(int));

  REP(i, 0, size2 - 1) {
    scanf("%d", (arr2.items + i));
    arr2.count++;
  }

  scanf("%d", &insert_index);
  scanf("%d", &insert_item);

  insertArray(arr1.items, arr1.capacity, insert_index, insert_item);
  deleteArray(arr1.items, arr1.capacity, remove_index);
  mergeArray(arr1.items, arr1.capacity, arr2.items, arr2.capacity);

  return 0;
}
