#include <stdbool.h>
#include <stdio.h>
#include <string.h>

void printArray(int *arr, int size) {
  int i;
  for (i = 0; i < size; i++) {
    printf("%d ", arr[i]);
  }

  printf("\n");
}

void insert_sort(int arr[], int n) {
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

  printf("Insert sort : ");
  printArray(arr, n);
}

void bubble_sort(int arr[], int n) {
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

  printf("Bubble sort : ");
  printArray(arr, n);
}

int binary(int arr[], int n, int target) {

  int left = 0;
  int right = n - 1;

  int final = 0;

  while (left <= right) {
    int mid = left + (right - left) / 2;
    if (arr[mid] == target) {
      final = mid;
      break;
    } else if (arr[mid] < target) {
      left = mid + 1;
    } else {
      right = mid - 1;
    }
  }

  if (left > right) {
    final = -1;
  }

  return final;
}

int gcd(int a, int b) {

  if (b == 0) {
    return a;
  } else {
    return gcd(b, a % b);
  }
}

void search_sort(int a[11], int arr[11], int b[11], int n, int target) {

  insert_sort(a, n);
  bubble_sort(b, n);

  int pos = binary(a, n, target);

  printf("position in arr : ");
  printf("target %d at a[%d]", target, pos);
  printf("\n");
}

int main() {
  int arr[11] = {3, 5, 7, 9, 29, 33, 17, 41, 26, 2, 8};
  int a[11], b[11];
  memcpy(&a, &arr, sizeof arr);
  memcpy(&b, &arr, sizeof arr);

  printf("mod 1 : \n");
  search_sort(a, arr, b, 11, 5);

  int gcd_results[] = {gcd(25, 75), gcd(39, 15), gcd(17, 50)};

  printf("gcd : ");
  printArray(gcd_results, sizeof(gcd_results) / sizeof(gcd_results[0]));

  return 0;
}
