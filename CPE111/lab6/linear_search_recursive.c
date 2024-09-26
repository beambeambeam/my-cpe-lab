#include <stdio.h>

int linear_search(int arr[], int i, int j, int x) {
  if (arr[i] == x) {
    return i + 1;
  } else if (i == j) {
    return -1;
  } else {
    return linear_search(arr, i + 1, j, x);
  }
}

int main() {
  int arr[] = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29};
  int n;
  scanf("%d", &n);

  int result = linear_search(arr, 0, 14, n);
  printf("%d", result);

  return 0;
}
