#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Node {
  int data;
  struct Node *next;
};

void traverse(struct Node *start) {
  if (start == NULL) {
    printf("none");
  }

  struct Node *ptr = start;
  while (ptr != NULL) {
    printf("%d ", ptr->data);
    ptr = ptr->next;
  }
}

int getsize(struct Node *start) {
  int size = 0;
  struct Node *ptr = start;
  while (ptr != NULL) {
    size++;
    ptr = ptr->next;
  }
  return size;
}

struct Node *INSH(struct Node **start) {
  struct Node *newNode;
  int val;
  scanf("%d", &val);

  newNode = (struct Node *)malloc(sizeof(struct Node));
  newNode->data = val;

  int size = getsize(*start);

  if (*start == NULL) {
    newNode->next = NULL;
  } else {
    newNode->next = *start;
  }

  *start = newNode;

  return newNode;
}

struct Node *INSE(struct Node **start) {
  struct Node *newNode, *ptr;
  int val;
  scanf("%d", &val);

  newNode = (struct Node *)malloc(sizeof(struct Node));
  newNode->data = val;
  newNode->next = NULL;

  if (*start == NULL) {
    *start = newNode;
    return *start;
  }

  ptr = *start;
  while (ptr->next != NULL) {
    ptr = ptr->next;
  }
  ptr->next = newNode;

  return *start;
}

struct Node *INSL(struct Node **start) {
  struct Node *newNode, *ptr, *preptr;
  int find, insert;
  scanf("%d %d", &find, &insert);

  newNode = (struct Node *)malloc(sizeof(struct Node));
  newNode->data = insert;

  if (*start == NULL) {
    return *start;
  }

  ptr = *start;
  preptr = NULL;

  while (ptr != NULL && ptr->data != find) {
    preptr = ptr;
    ptr = ptr->next;
  }

  if (ptr == NULL) {
    free(newNode);
    return *start;
  }

  if (preptr == NULL) {
    newNode->next = *start;
    *start = newNode;
  } else {
    preptr->next = newNode;
    newNode->next = ptr;
  }

  return *start;
}

struct Node *DELH(struct Node **start) {
  if (*start == NULL) {
    return NULL;
  }

  struct Node *temp = *start;
  *start = (*start)->next;
  free(temp);

  return *start;
};

struct Node *DELE(struct Node **start) {
  if (*start == NULL) {
    return NULL;
  }

  struct Node *ptr = *start;
  struct Node *preptr = NULL;

  while (ptr->next != NULL) {
    preptr = ptr;
    ptr = ptr->next;
  }

  if (preptr != NULL) {
    preptr->next = NULL;
  } else {
    *start = NULL;
  }

  free(ptr);

  return *start;
};

struct Node *DEL(struct Node **start) {
  if (*start == NULL) {
    return NULL;
  }

  int val;
  scanf("%d", &val);

  struct Node *ptr = *start;
  struct Node *preptr = NULL;

  while (ptr != NULL && ptr->data != val) {
    preptr = ptr;
    ptr = ptr->next;
  }

  if (ptr == NULL) {
    return *start;
  }

  if (preptr == NULL) {
    *start = ptr->next;
  } else {
    preptr->next = ptr->next;
  }

  free(ptr);

  return *start;
}

int main(int argc, char const *argv[]) {
  char choice[100];

  struct Node *start = NULL;

  while (1) {
    scanf("%s", choice);

    if (strcmp(choice, "INSH") == 0) {
      start = INSH(&start);
    }

    if (strcmp(choice, "INSE") == 0) {
      start = INSE(&start);
    }

    if (strcmp(choice, "INSL") == 0) {
      start = INSL(&start);
    }

    if (strcmp(choice, "DELH") == 0) {
      start = DELH(&start);
    }

    if (strcmp(choice, "DELE") == 0) {
      start = DELE(&start);
    }

    if (strcmp(choice, "DEL") == 0) {
      start = DEL(&start);
    }

    if (strcmp(choice, "END") == 0) {
      traverse(start);
      break;
    }
  }

  return 0;
}
