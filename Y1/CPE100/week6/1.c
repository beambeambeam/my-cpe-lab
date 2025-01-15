#include <stdio.h>

int main(int argc, char const *argv[]) {
  printf("Enter student scores (type -1 to start calculation)\n");
  int arr[10], count = 0, lowest = 0, lowest_index = 0;
  while (1) {
    printf("Enter score of student %d: ", count + 1);
    scanf("%d", &arr[count]);
    if (arr[count] == -1) {
      break;
    }
    count++;
  }

  int sum = 0;
  for (int i = 0; i < count; i++) {
    sum += arr[i];
    if (i == 0 || arr[i] < lowest) {
      lowest = arr[i];
      lowest_index = i;
    }
  }

  sum += 1;
  printf("The minimum score is %d.\n", lowest);
  printf("Student %d has minimum score\n", lowest_index + 1);

  return 0;
}
