#include <stdio.h>

int main() {
  int answer, guess;

  printf("Player 1, please enter a number: ");
  scanf("%d", &answer);

  while (1) {
    printf("Player 2, please make a guess: ");
    scanf("%d", &guess);

    if (guess < answer) {
      printf("%d is too low.\n", guess);
    } else if (guess > answer) {
      printf("%d is too high.\n", guess);
    } else {

      break;
    }
  }

  printf("Your answer is correct. End of program.");
  return 0;
}
