#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Node {
  int data;
  struct Node *next;
} Node;

Node *createNode(int new_data) {
  Node *new_node = (Node *)malloc(sizeof(Node));
  new_node->data = new_data;
  new_node->next = NULL;
  return new_node;
}

typedef struct Queue {
  Node *front, *rear;
} Queue;

Queue *createQueue() {
  Queue *q = (Queue *)malloc(sizeof(Queue));
  q->front = q->rear = NULL;
  return q;
}

int isEmpty(Queue *q) {
  if (q->front == NULL && q->rear == NULL) {
    return 1;
  }

  return 0;
}

void enqueue(Queue *q, int data) {
  Node *newNode = createNode(data);
  if (q->rear == NULL) {
    q->front = q->rear = newNode;
    return;
  }

  q->rear->next = newNode;
  q->rear = newNode;

  return;
}

void dequeue(Queue *q) {
  if (isEmpty(q)) {
    return;
  }

  Node *temp = q->front;
  q->front = q->front->next;

  if (q->front == NULL)
    q->rear = NULL;

  free(temp);

  return;
}

int getFront(Queue *q) {

  if (isEmpty(q)) {
    return INT_MIN;
  }
  return q->front->data;
}

void printQueue(Queue *q) {
  if (isEmpty(q)) {
    printf("none");
    return;
  }

  Node *temp = q->front;
  while (temp != NULL) {
    printf("%d ", temp->data);
    temp = temp->next;
  }
}

int main(int argc, char const *argv[]) {
  char choice[100];

  Queue *q = createQueue();

  int val;

  while (1) {
    scanf("%s", choice);

    if (strcmp(choice, "ENQ") == 0) {
      scanf("%d", &val);
      enqueue(q, val);
    }

    if (strcmp(choice, "DEQ") == 0) {
      dequeue(q);
    }

    if (strcmp(choice, "FRONT") == 0) {
      int front = getFront(q);
      if (front == INT_MIN) {
        printf("none\n");
      } else {
        printf("%d\n", front);
      }
    }

    if (strcmp(choice, "END") == 0) {
      printQueue(q);
      break;
    }
  }

  return 0;
}
