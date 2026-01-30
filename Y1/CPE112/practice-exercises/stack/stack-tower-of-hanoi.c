#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
  int data;
  struct Node *next;
} node;

node *createNode(int data) {
  node *newNode = (node *)malloc(sizeof(node));

  newNode->data = data;
  newNode->next = NULL;

  return newNode;
}

void push(node **head, int data) {
  node *newNode = createNode(data);

  if (*head == NULL) {
    *head = newNode;
    return;
  }

  newNode->next = *head;
  *head = newNode;
  return;
}

void pop(node **head) {
  node *temp = *head;
  *head = (*head)->next;
  free(temp);
  return;
}

int isEmpty(node **stack) { return *stack == NULL; }

void printStack(node **stack) {
  if (isEmpty(stack)) {
    printf("Empty Stack");
    return;
  }

  node *temp = *stack;
  while (temp != NULL) {
    printf("%d", temp->data);
    temp = temp->next;
    if (temp != NULL) {
      printf(" -> ");
    }
  }
  printf("\n");
}

int peak(node **stack) {
  if (!isEmpty(stack))
    return (*stack)->data;
  else
    return -1;
}

void moveDisks(int n, node **src, node **dest, node **aux, char srcRod,
               char destRod, char auxRod, int *total) {
  if (n == 1) {
    int disk = peak(src);
    pop(src);
    push(dest, disk);
    printf("Move disk %d from rod %c to rod %c\n", disk, srcRod, destRod);
    (*total)++;
    return;
  }

  moveDisks(n - 1, src, aux, dest, srcRod, auxRod, destRod, total);
  int disk = peak(src);
  pop(src);
  push(dest, disk);
  printf("Move disk %d from rod %c to rod %c\n", disk, srcRod, destRod);
  (*total)++;
  moveDisks(n - 1, aux, dest, src, auxRod, destRod, srcRod, total);
}

int main(int argc, char const *argv[]) {
  int n, total = 0;
  scanf("%d", &n);

  node *src = NULL;
  node *dest = NULL;
  node *aux = NULL;

  for (int i = n; i >= 1; i--) {
    push(&src, i);
  }

  moveDisks(n, &src, &dest, &aux, 'A', 'C', 'B', &total);
  printf("Total moves: %d\n", total);
}
