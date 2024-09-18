#include <stdio.h>

void printArray(int arr[], int size) {
  printf("\n");

  int i;
  for (i = 0; i < size; i++) {
    printf("%d ", arr[i]);
  }

  printf("\n");
}

int main(int argc, char const *argv[]) {
  int arr[] = {9, 8, 7, 6, 5, 4, 3, 2, 1};
  int n = sizeof(arr) / sizeof(arr[0]);
  int key;

  for (int i = 1; i < n; i++) {
    key = arr[i];

    for (int j = 0; j < i; j++) {
      if (arr[i] < arr[j]) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }
  }

  printArray(arr, n);

  return 0;
}
