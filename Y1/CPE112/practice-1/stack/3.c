#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
  char data;
  struct Node *next;
} node;

node *createNode(char data) {
  node *newNode = (node *)malloc(sizeof(node));

  newNode->data = data;
  newNode->next = NULL;

  return newNode;
}

void push(node **head, char data) {
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

char peak(node **stack) {
  if (!isEmpty(stack))
    return (*stack)->data;
  else
    return 'N';
}

int isPair(char check, char test) {
  if ((check == '(' && test == ')') || (check == '{' && test == '}') ||
      (check == '[' && test == ']'))
    return 1;
  else
    return -1;
}

int main(int argc, char const *argv[]) {
  node *stack = NULL;
  char word[100];
  scanf("%s", word);

  for (int i = 0; word[i] != '\0'; i++) {
    if ((word[i] == '(') || (word[i] == '{') || (word[i] == '[')) {
      push(&stack, word[i]);
      continue;
    }

    if (isPair(peak(&stack), word[i]) == 1) {
      pop(&stack);
    }
  }

  if (isEmpty(&stack)) {
    printf("True");
  } else {
    printf("False");
  }

  return 0;
}
