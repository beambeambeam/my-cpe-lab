#include <stdio.h>
int main(int argc, char const *argv[])
{
  printf("++ \n");
  int a = 5;
  printf("%d\n", a);
  printf("%d\n", a++);
  printf("%d\n", a);
  printf("%d\n", ++a);
  printf("%d\n", a);

  printf("-- \n");
  a = 5;
  printf("%d\n", a);
  printf("%d\n", a--);
  printf("%d\n", a);
  printf("%d\n", --a);
  printf("%d\n", a);

  return 0;
}
