#include <ctype.h>
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

void printReverse(node **stack) {
  if (isEmpty(stack)) {
    printf("none");
    return;
  }

  node *temp = *stack;
  node *prev = NULL;
  node *next = NULL;

  while (temp != NULL) {
    next = temp->next;
    temp->next = prev;
    prev = temp;
    temp = next;
  }

  temp = prev;
  while (temp != NULL) {
    printf("%c", temp->data);
    temp = temp->next;
  }

  temp = prev;
  prev = NULL;
  next = NULL;

  while (temp != NULL) {
    next = temp->next;
    temp->next = prev;
    prev = temp;
    temp = next;
  }

  *stack = prev;
}

int main(int argc, char const *argv[]) {
  char word[100];
  scanf("%s", word);

  node *stack = NULL;

  for (int i = 0; word[i] != '\0'; i++) {
    if (isEmpty(&stack)) {
      push(&stack, tolower(word[i]));
      continue;
    }

    char c = peak(&stack);
    if (c != tolower(word[i])) {
      push(&stack, tolower(word[i]));
    }
  }

  printReverse(&stack);

  return 0;
}
