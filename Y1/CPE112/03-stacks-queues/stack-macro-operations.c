#include <ctype.h>
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

char peak(node **stack) {
  if (!isEmpty(stack))
    return (*stack)->data;
  else
    return -1;
}

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

int main(int argc, char const *argv[]) {
  node *stack = NULL;

  push(&stack, 10);
  push(&stack, 20);
  push(&stack, 30);

  printf("Stack after pushes: ");
  printStack(&stack);

  pop(&stack);

  printf("Stack after pop: ");
  printStack(&stack);

  int top = peak(&stack);
  printf("Top element: %d\n", top);

  if (isEmpty(&stack)) {
    printf("Stack is empty\n");
  } else {
    printf("Stack is not empty\n");
  }

  return 0;
}
