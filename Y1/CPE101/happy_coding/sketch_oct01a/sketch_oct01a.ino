#define LEDPIN 2
#define LEDPIN2 3

void setup() {
  pinMode(LEDPIN, OUTPUT);
}



void loop() {
  while (1) {
    digitalWrite(LEDPIN, HIGH);
    digitalWrite(LEDPIN2, HIGH);
    delay(100);
    digitalWrite(LEDPIN, LOW);
    delay(100);
    digitalWrite(LEDPIN2, LOW);
    delay(100);
  }
}
