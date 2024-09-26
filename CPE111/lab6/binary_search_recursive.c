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

int main() {
  int arr[] = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29};
  int n = sizeof(arr) / sizeof(arr[0]);
  int target;

  scanf("%d", &target);

  int result = binarySearch(arr, 0, n - 1, target);

  printf("%d\n", result);

  return 0;
}
