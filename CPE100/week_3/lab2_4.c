#include <stdio.h>

int main()
{
  int test;
  printf("Test specific format of a variable\n");
  printf("Enter value of test: ");
  scanf("%lf", &test);
  printf("Print format of test\n");
  printf("int %%d = %d\n", test);
  printf("float %%f = %f\n", test);
  printf("double %%lf = %lf\n", test);
  printf("exponent %%e = %e\n", test);
  printf("significant %%g = %g\n", test);
  printf("character %%c = %c\n", test);
   printf("End program");
  return 0;
}
