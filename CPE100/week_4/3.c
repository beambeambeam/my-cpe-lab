#include <stdio.h>

void print_card(int age) {
  if (age <= 12) {
    printf("Give kid card to the passenger.\n");
  } else if (age <= 25) {
    printf("Give student card to the passenger.\n");
  } else if (age <= 60) {
    printf("Give adult card to the passenger.\n");
  } else {
    printf("Give normal card to the passenger.\n");
  }
}

int main() {
  printf("Enter passenger's age : ");
  int age;
  scanf("%d", &age);
  print_card(age);

  char c;
  while (1) {
    printf("Do you want to continue (y/n)? : ");
    scanf(" %c", &c);
    if (c == 'n') {
      break;
    }

    printf("Enter passenger's age : ");
    scanf("%d", &age);
    print_card(age);
  }

  return 0;
}
