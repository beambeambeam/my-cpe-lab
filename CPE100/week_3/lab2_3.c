#include <stdio.h> // add < and > to include statement
// Fix the code below until there is no error
int main() {
  int a = 0, b;
  float c = 2.5;
  char d = 'A'; // single quote
  double e; // semi-colon
  d = 'D'; // single quote it
  e = 10; // variable type
  printf("Enter variable b: \n"); // double quote it
  scanf("%d", &b); // double quote %d and change parenthesis to &b
  printf("a = %d, b = %d, c = %f, d = %c, e = %d\n", a, b, c, d, e); // b from %f to %d
}