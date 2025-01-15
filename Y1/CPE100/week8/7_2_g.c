#include <stdio.h>

void printMatrix(int M[][10], int row, int col) {
  int i, j;
  printf("\nPrint Matrix:\n");
  for (i = 0; i < row; i++) {
    for (j = 0; j < col; j++) {
      printf("%d", M[i][j]);
    }
    if (i != row - 1)
      printf("\n");
  }

  printf("\n");
}

void readMatrix(int M[][10], int *row, int *col) {
  int i, j;
  printf("\nEnter no. of rows and columns of matrix: ");
  scanf("%d%d", row, col);
  printf("\nInput elements of matrix [%d %d]:\n", *row, *col);
  for (i = 0; i < *row; i++)
    for (j = 0; j < *col; j++) {
      printf("M[%d][%d]: ", i, j);
      scanf("%d", &M[i][j]);
    }
  printMatrix(M, *row, *col);
}

int main() {
  int M1[10][10], M2[10][10];
  int rowM1, colM1, rowM2, colM2;
  rowM1 = colM1 = rowM2 = colM2 = 0;
  printf("Read Matrix M1:\n");
  readMatrix(M1, &rowM1, &colM1);
  printf("Read Matrix M2:\n");
  readMatrix(M2, &rowM2, &colM2);
  printf("\nM1 dimension: row = %d, col = %d", rowM1, colM1);
  printf("\nM2 dimension: row = %d, col = %d", rowM2, colM2);
  return 0;
}
