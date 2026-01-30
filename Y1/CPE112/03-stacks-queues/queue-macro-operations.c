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

void traverse(Node *start) {
  Node *ptr = start;
  while (ptr != NULL) {
    printf("%d ", ptr->data);
    ptr = ptr->next;
  }
  printf("\n");
}

int main(int argc, char const *argv[]) {
  Queue *q = createQueue();

  enqueue(q, 10);
  enqueue(q, 20);
  enqueue(q, 30);

  traverse(q->front);

  printf("Front element is %d\n", getFront(q));
  printf("Rear element is %d\n", getRear(q));

  dequeue(q);
  printf("After dequeue, front element is %d\n", getFront(q));

  dequeue(q);
  dequeue(q);
  dequeue(q);

  free(q);
  return 0;
}
