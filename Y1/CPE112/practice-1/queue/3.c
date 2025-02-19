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
  int r, t, n;
  scanf("%d %d %d", &r, &t, &n);

  Queue *q = createQueue();
  Queue *in = createQueue();

  int x;
  while (1) {
    scanf("%d", &x);
    if (x == -1)
      break;

    enqueue(q, x);
  }

  int process = 0, start_size = q->size;

  while (1) {
    n = n - t;
    if (n <= 0) {
      break;
    }
    int ram = r;

    while (!isEmpty(q)) {
      int front = getFront(q);
      if (ram < front) {
        break;
      }

      ram = ram - front;

      dequeue(q);
    }
  }

  printf("%d", start_size - q->size);

  return 0;
}
