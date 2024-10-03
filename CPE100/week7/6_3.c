#define MAX_STUDENT 10
#define MAX_NAME 8

#include <stdio.h>
#include <string.h>

int main(int argc, char const *argv[]) {
  char names[MAX_STUDENT][MAX_NAME + 1];
  printf("Enter student names (type END to print all names)\n");

  int count = 0;

  while (count < MAX_STUDENT) {
    printf("Enter name of student %d: ", count + 1);
    scanf("%s", names[count]);
    if (strcmp(names[count], "END") == 0) {
      break;
    }
    if (strlen(names[count]) > MAX_NAME) {
      printf("The name is too long. Please try again.\n");
      continue;
    }

    count++;
  }

  printf("Name list: ");
  for (int i = 0; i < count; i++) {
    if (i == count - 1) {
      printf("%s", names[i]);
    } else {
      printf("%s ", names[i]);
    }
  }

  return 0;
}
