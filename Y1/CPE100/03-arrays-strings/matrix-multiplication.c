#include <stdio.h>

int main(int argc, char const *argv[]) {
  printf("Matrix Multiplication\n");
  int m1_row, m1_col, m2_row, m2_col;
  printf("Enter matrix dimension of M1 (rows columns): ");
  scanf("%d %d", &m1_row, &m1_col);

  int m1[m1_row][m1_col];

  printf("Enter M1 matrix elements:\n");
  for (int i = 0; i < m1_row; i++) {
    for (int j = 0; j < m1_col; j++) {
      scanf("%d", &m1[i][j]);
    }
  }

  printf("Enter matrix dimension of M2 (rows columns): ");
  scanf("%d %d", &m2_row, &m2_col);

  int m2[m2_row][m2_col], result[m1_row][m2_col];

  printf("Enter M2 matrix elements:\n");
  for (int i = 0; i < m2_row; i++) {
    for (int j = 0; j < m2_col; j++) {
      scanf("%d", &m2[i][j]);
    }
  }

  if (m1_col != m2_row) {
    printf("Invalid matrices dimension. The 2 matrices must have the same "
           "dimension.\n");
    return 1;
  }

  for (int i = 0; i < m1_row; i++) {
    for (int j = 0; j < m2_col; j++) {
      result[i][j] = 0;
      for (int k = 0; k < m1_col; k++) {
        result[i][j] += m1[i][k] * m2[k][j];
      }
    }
  }

  printf("Resultant of Matrix Mb (M1 * M2):\n");
  for (int i = 0; i < m1_row; i++) {
    for (int j = 0; j < m2_col; j++) {
      printf("%d ", result[i][j]);
    }
    printf("\n");
  }

  return 0;
}
