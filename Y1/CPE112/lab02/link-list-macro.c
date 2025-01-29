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

struct Node *insertBegin(struct Node **start, char val) {
  struct Node *newNode;

  newNode = (struct Node *)malloc(sizeof(struct Node));
  if (newNode == NULL) {
    printf("Memory allocation failed\n");
    exit(1);
  }

  newNode->data = val;
  newNode->next = *start;
  *start = newNode;

  return newNode;
}

struct Node *insertEnd(struct Node **start, char val) {
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

  return newNode;
}

struct Node *insertAfter(struct Node **start, char val) {
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

// struct Node *insertBefore(struct Node **start, struct Node *nextNode,
//                           char val) {
//   struct Node *newNode, *ptr;

//   newNode = (struct Node *)malloc(sizeof(struct Node));
//   if (newNode == NULL) {
//     printf("Memory allocation failed\n");
//     exit(1);
//   }

//   newNode->data = val;
//   newNode->next = nextNode;

//   if (*start == nextNode) {
//     *start = newNode;
//   } else {
//     ptr = *start;
//     while (ptr->next != nextNode) {
//       ptr = ptr->next;
//     }
//     ptr->next = newNode;
//   }

//   return newNode;
// }

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
  struct Node *nodeNew1 = insertBegin(&start, 'E');
  *start = *nodeNew1;

  traverse(nodeNew1);

  struct Node *nodeNew2 = insertEnd(&nodeNew1, 'F');

  traverse(start);

  struct Node *nodeNew3 = insertAfter(&nodeNew1, 'G');

  traverse(start);

  // struct Node *after = &node3;
  // struct Node *nodeNew4 = insertBefore(&after, nodeNew3, 'H');

  // traverse(start);

  return 0;
}
