#include <stdio.h>

int main(int argc, char const *argv[]) {
  printf("Enter a word: ");
  char word[100];
  scanf("%s", word);

  int i = 0, count = 0;
  while (word[i] != '\0') {
    if (word[i] == 'a') {
      count++;
    }
    i++;
  }

  printf("Total number of 'a' in the word: %d\n", count);

  return 0;
}
