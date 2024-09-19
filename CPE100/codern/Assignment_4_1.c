// 67070501045 supawit marayat
#include <stdio.h>

int main() {
  int n, m;
  char choice;
  int i;

  do {
    printf("Enter start number: ");
    scanf("%d", &n);
    printf("Enter end number: ");
    scanf("%d", &m);

    for (int j = 0; j < n; j++) {
      for (i = 0; i <= (m - n); i++) {
        printf("%d ", (n + i) * (j + 1));
      }
      printf("\n");
    }

    printf("Do you want to continue (y/n)? ");
    scanf(" %c", &choice);
  } while (choice == 'y' || choice == 'Y');

  printf("End of program. Goodbye.\n");

  return 0;
}
