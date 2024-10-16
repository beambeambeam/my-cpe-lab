#include <stdio.h>

#define INF 99999
#define V 5

void warshall(int target[V][V]) {
  for (int k = 0; k < V; k++) {
    for (int i = 0; i < V; i++) {
      for (int j = 0; j < V; j++) {
        if (target[i][k] + target[k][j] < target[i][j]) {
          target[i][j] = target[i][k] + target[k][j];
        }
      }
    }
  }
}

void printMatrix(int target[V][V]) {
  for (int i = 0; i < 5; i++) {
    for (int j = 0; j < 5; j++) {
      if (target[i][j] == INF) {
        printf("0 ");
      } else {
        printf("%d ", target[i][j]);
      }
    }
    printf("\n");
  }
}

int main(int argc, char const *argv[]) {
  int target[V][V] = {{0, 1, INF, 1, INF},
                      {INF, 0, 1, INF, 1},
                      {INF, 1, 0, INF, 1},
                      {1, INF, 1, 0, INF},
                      {INF, 1, INF, 1, 0}};

  printf("Original matrix\n");
  printMatrix(target);

  warshall(target);

  printf("After warshall\n");
  printMatrix(target);

  return 0;
}
