#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
  char data;
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

int main(int argc, char const *argv[]) {
  Queue *q1 = createQueue();
  Queue *q2 = createQueue();
  Queue *q3 = createQueue();

  char word[100];
  fgets(word, sizeof(word), stdin);

  for (int i = 0; word[i] != '\0'; i++) {
    if ((word[i] >= 'a' && word[i] <= 'z') ||
        (word[i] >= 'A' && word[i] <= 'Z')) {
      char ch = word[i];
      if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
          ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
        enqueue(q3, ch);
      } else {
        enqueue(q1, ch);
      }
    } else {
      enqueue(q2, word[i]);
    }
  }

  while (!isEmpty(q3)) {
    int front = getFront(q3);

    printf("%c", front);

    dequeue(q3);
  }

  while (!isEmpty(q2)) {
    int front = getFront(q2);

    printf("%c", front);

    dequeue(q2);
  }

  while (!isEmpty(q1)) {
    int front = getFront(q1);

    printf("%c", front);

    dequeue(q1);
  }

  return 0;
}
