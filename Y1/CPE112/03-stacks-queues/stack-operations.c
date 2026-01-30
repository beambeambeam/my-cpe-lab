#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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
  if (*head == NULL) {
    return;
  }
  node *temp = *head;
  *head = (*head)->next;
  free(temp);
  return;
}

int isEmpty(node **stack) { return *stack == NULL; }

void printStack(node **stack) {
  if (isEmpty(stack)) {
    printf("none");
    return;
  }

  node *temp = *stack;
  while (temp != NULL) {
    printf("%d ", temp->data);
    temp = temp->next;
  }
}

int peak(node **stack) {
  if (!isEmpty(stack))
    return (*stack)->data;
  else
    return -1;
}

int main(int argc, char const *argv[]) {
  char choice[100];

  node *stack = NULL;

  int val;

  while (1) {
    scanf("%s", choice);

    if (strcmp(choice, "PUSH") == 0) {
      scanf("%d", &val);
      push(&stack, val);
    }

    if (strcmp(choice, "PEEK") == 0) {
      int nums = peak(&stack);
      if (nums == -1) {
        printf("none\n");
      } else {
        printf("%d\n", nums);
      }
    }

    if (strcmp(choice, "POP") == 0) {
      pop(&stack);
    }

    if (strcmp(choice, "END") == 0) {
      printStack(&stack);
      break;
    }
  }
}
