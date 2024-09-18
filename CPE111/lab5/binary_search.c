#include <stdio.h>

int main(int argc, char const *argv[]) {
  int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
  int n = sizeof(arr) / sizeof(arr[0]);
  int target = 5;

  int left = 0;
  int right = n - 1;

  while (left <= right) {
    int mid = left + (right - left) / 2;
    if (arr[mid] == target) {
      printf("Found %d at index %d.\n", target, mid);
      break;
    } else if (arr[mid] < target) {
      left = mid + 1;
    } else {
      right = mid - 1;
    }
  }

  if (left > right) {
    printf("Not found %d.\n", target);
  }

  return 0;
}