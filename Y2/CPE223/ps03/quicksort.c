#include <stdio.h>

static void swap_int(int *a, int *b) {
  int tmp = *a;
  *a = *b;
  *b = tmp;
}

static int partition(int *arr, int left, int right) {
  const int pivot = arr[right];
  int i = left - 1;

  for (int j = left; j < right; j++) {
    if (arr[j] <= pivot) {
      i++;
      swap_int(&arr[i], &arr[j]);
    }
  }

  swap_int(&arr[i + 1], &arr[right]);
  return i + 1;
}

static void quicksort(int *arr, int left, int right) {
  if (left >= right) {
    return;
  }

  const int p = partition(arr, left, right);
  quicksort(arr, left, p - 1);
  quicksort(arr, p + 1, right);
}

int main() {
  int data[] = {10, 12, 8, 1, 5, 7, 11, 6, 8};
  const int n = (int)(sizeof(data) / sizeof(data[0]));

  quicksort(data, 0, n - 1);

  for (int i = 0; i < n; i++) {
    printf("%d%s", data[i], (i + 1 == n) ? "\n" : " ");
  }

  return 0;
}
