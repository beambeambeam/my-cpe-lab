#include <stdio.h>
int main(void) {
  int score[10];
  int mini = 100, n = 1, k = 0;
  printf("Input student scores (type -1 to start calculation)\n");
  for (int i = 0; i < 10; i++) {
    printf("Enter score of student %d: ", n);
    scanf("%d", &score[i]);
    if (score[i] == -1) {
      break;
    } else {
      if (mini > score[i]) {
        mini = score[i];
        k = n;
      }
    }
    n = n + 1;
  }
  printf("The minium score is %d", mini);
  printf("\nStudent %d has minimum score.", k);
}
