void setup() {
  pinMode(5, INPUT);
  pinMode(2,OUTPUT);
  Serial.begin(115200);
}

void loop() {
  float ldr = analogRead(5);
  Serial.println(ldr);
  if (ldr>400) {
    digitalWrite(2, LOW);
  } else {
    digitalWrite(2, HIGH);
  }
  delay(500);
}
