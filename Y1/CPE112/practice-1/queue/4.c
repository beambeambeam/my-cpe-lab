#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
  char data;
  struct Node *next;
} Node;

Node *createNode(char new_data) {
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

void enqueue(Queue *q, char data) {
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

void dequeueRear(Queue *q) {
  if (isEmpty(q)) {
    printf("Queue Underflow\n");
    return;
  }

  if (q->front == q->rear) {
    free(q->rear);
    q->front = q->rear = NULL;
  } else {
    Node *temp = q->front;
    while (temp->next != q->rear) {
      temp = temp->next;
    }
    free(q->rear);
    q->rear = temp;
    q->rear->next = NULL;
  }

  q->size--;
}

int getFront(Queue *q) {

  if (isEmpty(q)) {
    return INT_MIN;
  }
  return q->front->data;
}

int getRear(Queue *q) {

  if (isEmpty(q)) {
    return INT_MIN;
  }
  return q->rear->data;
}

void printQueue(Queue *q) {
  if (isEmpty(q)) {
    return;
  }

  Node *temp = q->front;
  while (temp != NULL) {
    printf("%c ", temp->data);
    temp = temp->next;
  }
}

int main(int argc, char const *argv[]) {
  char word[100];
  scanf("%s", word);

  Queue *q = createQueue();

  for (int i = 0; word[i] != '\0'; i++) {
    enqueue(q, word[i]);
  }

  if (q->size % 2 != 0) {
    printf("NO");
  }

  while (getFront(q) == getRear(q) && !isEmpty(q)) {
    dequeue(q);
    dequeueRear(q);
  }

  if (isEmpty(q)) {
    printf("YES");
    return 0;
  }

  printf("NO");

  return 0;
}
