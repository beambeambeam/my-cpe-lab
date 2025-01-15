// 67070501045 supawit marayat

#include <stdio.h>
#include <string.h>

int main(int argc, char const *argv[]) {
  char str[100];
  printf("Enter a word: ");
  scanf("%[^\n]s", str);
  printf("Total alphabets in '%s' = %d.", str, strlen(str));
  return 0;
}
