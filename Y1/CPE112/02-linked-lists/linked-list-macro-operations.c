#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Node {
  char data;
  struct Node *next;
};

void traverse(struct Node *start) {
  struct Node *ptr = start;
  while (ptr != NULL) {
    printf("%c", ptr->data);
    ptr = ptr->next;
  }
  printf("\n");
}

void insertBegin(struct Node **start, char val) {
  struct Node *newNode;

  newNode = (struct Node *)malloc(sizeof(struct Node));
  if (newNode == NULL) {
    printf("Memory allocation failed\n");
    exit(1);
  }

  newNode->data = val;
  newNode->next = *start;
  *start = newNode;
}

void insertEnd(struct Node **start, char val) {
  struct Node *newNode, *ptr;

  newNode = (struct Node *)malloc(sizeof(struct Node));
  if (newNode == NULL) {
    printf("Memory allocation failed\n");
    exit(1);
  }

  newNode->data = val;
  newNode->next = NULL;

  if (*start == NULL) {
    *start = newNode;
  } else {
    ptr = *start;
    while (ptr->next != NULL) {
      ptr = ptr->next;
    }
    ptr->next = newNode;
  }
}

void insertAfter(struct Node **start, char val) {
  struct Node *newNode, *ptr, *prePtr;

  newNode = (struct Node *)malloc(sizeof(struct Node));
  if (newNode == NULL) {
    printf("Memory allocation failed\n");
    exit(1);
  }

  newNode->data = val;
  newNode->next = NULL;

  ptr = *start;
  prePtr = ptr->next;

  ptr->next = newNode;
  newNode->next = prePtr;
}

void insertBefore(struct Node **start, struct Node *nextNode, char val) {
  struct Node *newNode, *ptr;

  newNode = (struct Node *)malloc(sizeof(struct Node));
  if (newNode == NULL) {
    printf("Memory allocation failed\n");
    exit(1);
  }

  newNode->data = val;

  if (*start == nextNode) {
    newNode->next = *start;
    *start = newNode;
    return;
  }

  ptr = *start;
  while (ptr != NULL && ptr->next != nextNode) {
    ptr = ptr->next;
  }

  if (ptr == NULL) {
    printf("The given next node is not present in the list\n");
    free(newNode);
    return;
  }

  newNode->next = nextNode;
  ptr->next = newNode;
}

void deleteFirst(struct Node **start) {
  if (*start == NULL) {
    printf("List is empty\n");
    return;
  }

  struct Node *temp = *start;
  *start = (*start)->next;
  free(temp);
}

void deleteLast(struct Node **start) {
  if (*start == NULL) {
    printf("List is empty\n");
    return;
  }

  if ((*start)->next == NULL) {
    free(*start);
    *start = NULL;
    return;
  }

  struct Node *ptr = *start;
  while (ptr->next->next != NULL) {
    ptr = ptr->next;
  }

  free(ptr->next);
  ptr->next = NULL;
}

void deleteBefore(struct Node **start, struct Node *nextNode) {
  if (*start == NULL || *start == nextNode) {
    printf("No node exists before the given node\n");
    return;
  }

  struct Node *ptr = *start;
  struct Node *prePtr = NULL;

  while (ptr->next != NULL && ptr->next != nextNode) {
    prePtr = ptr;
    ptr = ptr->next;
  }

  if (ptr->next == NULL) {
    printf("The given next node is not present in the list\n");
    return;
  }

  if (prePtr == NULL) {
    *start = nextNode;
  } else {
    prePtr->next = nextNode;
  }

  free(ptr);
}

void deleteAfter(struct Node **start, struct Node *prevNode) {
  if (prevNode == NULL || prevNode->next == NULL) {
    printf("No node exists after the given node\n");
    return;
  }

  struct Node *temp = prevNode->next;
  prevNode->next = temp->next;
  free(temp);
}

int main(int argc, char const *argv[]) {
  struct Node node1 = {'A', NULL};
  struct Node node2 = {'B', NULL};
  struct Node node3 = {'C', NULL};
  struct Node node4 = {'D', NULL};

  node1.next = &node2;
  node2.next = &node3;
  node3.next = &node4;

  traverse(&node1);

  struct Node *start = &node1;
  insertBegin(&start, 'E');

  traverse(start);

  insertEnd(&start, 'F');

  traverse(start);

  insertAfter(&start, 'G');

  traverse(start);

  deleteFirst(&start);
  traverse(start);

  deleteLast(&start);
  traverse(start);

  deleteBefore(&start, &node3);
  traverse(start);

  deleteAfter(&start, &node2);
  traverse(start);

  return 0;
}
