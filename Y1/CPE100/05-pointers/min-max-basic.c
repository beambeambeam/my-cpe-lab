#include <stdio.h>

int min(int num1, int num2) {
  if (num1 < num2)
    return num1;
  else
    return num2;
}
int max(int num1, int num2) {
  if (num1 > num2)
    return num1;
  else
    return num2;
}

int main() {
  int n1, n2;
  int i;
  printf("Enter 2 numbers: ");
  scanf("%d %d", &n1, &n2);

  while (1) {
    printf("Choose what to do from options below\n");
    printf("1. Find minimum\n");
    printf("2. Find maximum\n");
    printf("3. Exit program\n");
    printf("Enter option no.: ");
    int option;
    scanf("%d", &option);
    switch (option) {
    case 1:
      printf("Minimum value is: %d\n", min(n1, n2));
      break;
    case 2:
      printf("Maximum value is: %d\n", max(n1, n2));
      break;
    }
    if (option == 3)
      break;
  }
  printf("End of program. Goodbye.\n");
  return 0;
}
