// 67070501045
// 67070501052
#include <stdio.h>

int binarySearch(int arr[], int left, int right, int target) {
  if (left <= right) {
    int mid = left + (right - left) / 2;

    if (arr[mid] == target)
      return mid + 1;

    if (arr[mid] > target)
      return binarySearch(arr, left, mid - 1, target);

    return binarySearch(arr, mid + 1, right, target);
  }

  return -1;
}

int linear_search(int arr[], int i, int j, int x) {
  if (arr[i] == x) {
    return i + 1;
  } else if (i == j) {
    return -1;
  } else {
    return linear_search(arr, i + 1, j, x);
  }
}

void fibonacci(int n) {
  if (n == 0)
    printf("0");
  int fiboarray[500];
  fiboarray[0] = 1;
  fiboarray[1] = 1;
  for (int i = 2; i < n; i++) {
    fiboarray[i] = fiboarray[i - 1] + fiboarray[i - 2];
  }
  for (int j = 0; j < (n - 1); j++) {
    printf("%d ", fiboarray[j]);
  }
  printf("%d", fiboarray[n - 1]);
}

int main() {
  int arr[] = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29};
  int n;
  scanf("%d", &n);
  printf("%d, %d", binarySearch(arr, 0, 14, n), linear_search(arr, 0, 14, n));
  printf("\n");

  int f;
  scanf("%d", &f);
  fibonacci(f);

  return 0;
}
