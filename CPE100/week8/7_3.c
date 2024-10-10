#include <stdio.h>

void test_function() {
  int a;
  printf("Address of a in the Test Function is %p\n", &a);
}

int main() {
  // Part 1
  double num[5] = {1.1, 2.2, 3.3, 4.4, 5.5};
  double *p = num;
  printf("p is %p\n", p);
  printf("num is %p\n", num);
  printf("Address of num[0] is %p\n", &num[0]);
  printf("Address of num[1] is %p\n", &num[1]);
  printf("Address of num[2] is %p\n", &num[2]);
  printf("Value of num[0] is %lf\n", *p);
  printf("Value of num[1] is %lf\n", *(p + 1));
  printf("Value of num[2] is %lf\n", *(p + 2));
  printf("Value of num[0] is %lf\n", *num);
  printf("Value of num[1] is %lf\n", *(num + 1));
  printf("Value of num[2] is %lf\n", *(num + 2));
  // ** The address of num[0] is the same as the address of num
  // ** The value of num[0] is the same as the value of *num
  // ** The value of num[n] is the same as the value of *(num + n)
  // ** The address of num[n] is the same as the address of &num[n]
  // ** The address of num[n] is the same as the address of num + n

  // Part 2
  int a;
  printf("Address of a in the Main Function is %p\n", &a);
  test_function();
  int b;
  printf("Address of b in the Main Function is %p\n", &b);
  // ** The address of a in the Test Function is different from the address of a
  // in the Main Function

  // Part 3
  char word[3][5]; // 3 words, 5 characters each
  printf("Address of word[0] is %p\n", word[0]);
  printf("Address of word[1] is %p\n", word[1]);
  printf("Address of word[2] is %p\n", word[2]);
  printf("Value of word[0] is %s\n", word[0]);
  printf("Value of word[1] is %s\n", word[1]);
  printf("Value of word[2] is %s\n", word[2]);
  // ** The address of word[n] is the same as the address of &word[n]
  // ** The address of word[n] is the same as the address of word + n
  // ** The value of word[n] is the same as the value of word[n][0]
  // ** The value of word[n] is the same as the value of *word[n]
  // ** The value of word[n] is the same as the value of *(word + n)
  return 0;
}
