#include <stdbool.h>
#include <stdio.h>

void printArray(int arr[], int size) {
  printf("\n");

  int i;
  for (i = 0; i < size; i++)
    printf("%d ", arr[i]);

  printf("\n");
}

int main(int argc, char const *argv[]) {
  int arr[] = {9, 8, 7, 6, 5, 4, 3, 2, 1};
  int n = sizeof(arr) / sizeof(arr[0]);
  bool swapped;

  for (int i = 0; i < n - 1; i++) {
    swapped = false;
    for (int j = 0; j < n - i - 1; j++) {
      if (arr[j] > arr[j + 1]) {
        int temp = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = temp;
        swapped = true;
      }
    }

    if (swapped == false) {
      break;
    }
  }

  printArray(arr, n);

  return 0;
}
