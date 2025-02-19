#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
  int data;
  int priority;
  struct Node *next;
} Node;

Node *createNode(int new_data, int priority) {
  Node *new_node = (Node *)malloc(sizeof(Node));
  new_node->data = new_data;
  new_node->priority = priority;
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

int isEmpty(Queue *q) { return q->front == NULL; }

void enqueue(Queue *q, int data, int priority) {
  Node *newNode = createNode(data, priority);
  if (isEmpty(q) || q->front->priority > priority) {
    newNode->next = q->front;
    q->front = newNode;
  } else {
    Node *temp = q->front;
    while (temp->next != NULL && temp->next->priority <= priority) {
      temp = temp->next;
    }
    newNode->next = temp->next;
    temp->next = newNode;
  }
  if (newNode->next == NULL) {
    q->rear = newNode;
  }
  q->size++;
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

  enqueue(q, 10, 2);
  enqueue(q, 20, 1);
  enqueue(q, 30, 3);
  enqueue(q, 40, 1);

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
