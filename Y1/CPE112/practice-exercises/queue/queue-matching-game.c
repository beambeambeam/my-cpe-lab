#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

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
  int size;
} Queue;

Queue *createQueue() {
  Queue *q = (Queue *)malloc(sizeof(Queue));
  q->front = q->rear = NULL;
  q->size = 0;
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
    q->size++;
    return;
  }

  q->rear->next = newNode;
  q->rear = newNode;

  q->size++;

  return;
}

void dequeue(Queue *q) {
  if (isEmpty(q)) {
    printf("Queue Underflow\n");
    return;
  }

  Node *temp = q->front;
  q->front = q->front->next;

  if (q->front == NULL)
    q->rear = NULL;

  free(temp);
  q->size--;

  return;
}

int getFront(Queue *q) {

  if (isEmpty(q)) {
    printf("Queue is empty\n");
    return INT_MIN;
  }
  return q->front->data;
}

int getRear(Queue *q) {

  if (isEmpty(q)) {
    printf("Queue is empty\n");
    return INT_MIN;
  }
  return q->rear->data;
}

void printQueue(Queue *q) {
  if (isEmpty(q)) {
    printf("Queue is empty\n");
    return;
  }

  Node *temp = q->front;
  while (temp != NULL) {
    printf("%d ", temp->data);
    temp = temp->next;
  }
}

int main(int argc, char const *argv[]) {
  int n;
  scanf("%d", &n);

  Queue *qs = createQueue();
  Queue *qp = createQueue();

  int x;
  for (int i = 0; i < n; i++) {
    scanf("%d", &x);
    enqueue(qs, x);
  }

  for (int i = 0; i < n; i++) {
    scanf("%d", &x);
    enqueue(qp, x);
  }

  while (!isEmpty(qs)) {
    int front_s = getFront(qs);
    int front_p = getFront(qp);

    if (front_s != front_p) {
      dequeue(qs);
      enqueue(qs, front_s);

      continue;
    } else {
      dequeue(qs);
      dequeue(qp);
    }

    if (qp->size == 0 && qs->size == 0) {
      break;
    }

    // can't find final break case
  }

  printf("%d", qs->size);

  return 0;
}
