void setup()
{
  pinMode(9, OUTPUT);
}

void DO_()
{
  for (int i = 0; i < 100; i++)
  {
    digitalWrite(9, HIGH);
    delayMicroseconds(1911);
    digitalWrite(9, LOW);
    delayMicroseconds(1911);
  }
}

void RE_()
{
  for (int i = 0; i < 100; i++)
  {
    digitalWrite(9, HIGH);
    delayMicroseconds(1700);
    digitalWrite(9, LOW);
    delayMicroseconds(1700);
  }
}

void ME_()
{
  for (int i = 0; i < 100; i++)
  {
    digitalWrite(9, HIGH);
    delayMicroseconds(1519);
    digitalWrite(9, LOW);
    delayMicroseconds(1519);
  }
}

void FA_()
{
  for (int i = 0; i < 100; i++)
  {
    digitalWrite(9, HIGH);
    delayMicroseconds(1432);
    digitalWrite(9, LOW);
    delayMicroseconds(1432);
  }
}

void SON_()
{
  for (int i = 0; i < 100; i++)
  {
    digitalWrite(9, HIGH);
    delayMicroseconds(1275);
    digitalWrite(9, LOW);
    delayMicroseconds(1275);
  }
}
void loop()
{
  SON_();
  delay(300);
  SON_();
  delay(300);
  SON_();
  delay(300);

  SON_();
  delay(100);
  ME_();
  delay(200);

  RE_();
  delay(100);
  ME_();
  delay(100);
  SON_();
  delay(100);
  DO_();
  delay(1000);
}
