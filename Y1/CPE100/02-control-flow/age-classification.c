#include <stdio.h>

int main() {
  printf("Enter passenger's age : ");
  int age;
  scanf("%d", &age);

  if (age <= 12) {
    printf("Give kid card to the passenger.\n");
  } else if (age <= 25) {
    printf("Give student card to the passenger.\n");
  } else if (age >= 60) {
    printf("Give elder card to the passenger.\n");
  } else {
    printf("Give normal card to the passenger.\n");
  }

  return 0;
}