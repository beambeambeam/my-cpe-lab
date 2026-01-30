#include <SoftwareSerial.h>
#define rxPin 3
#define txPin 2

SoftwareSerial mySerial = SoftwareSerial(rxPin, txPin);

void setup()
{
  digitalWrite(txPin, HIGH);
  delay(1000);

  pinMode(rxPin, INPUT);
  pinMode(txPin, OUTPUT);

  mySerial.begin(9600);

  mySerial.write(128);
  mySerial.write(129);
  mySerial.write(0xFE);
  mySerial.write(0x38);
  mySerial.write(0xFE);
  mySerial.write(0xC0);

  delay(2000);
}

void loop()
{
  mySerial.write(130);
  mySerial.write("CPE#38");
  mySerial.write(154);
  mySerial.write("KMUTT           ");
  delay(500);
  mySerial.write(130);
  mySerial.write("CPE#38");
  mySerial.write(154);
  mySerial.write(" KMUTT          ");
  delay(500);
  mySerial.write(130);
  mySerial.write("CPE#38");
  mySerial.write(154);
  mySerial.write("  KMUTT         ");
  delay(500);
  mySerial.write(130);
  mySerial.write("CPE#38");
  mySerial.write(154);
  mySerial.write("   KMUTT        ");
  delay(500);
  mySerial.write(130);
  mySerial.write("CPE#38");
  mySerial.write(154);
  mySerial.write("    KMUTT       ");
  delay(500);
  mySerial.write(130);
  mySerial.write("CPE#38");
  mySerial.write(154);
  mySerial.write("     KMUTT      ");
  delay(500);
  mySerial.write(130);
  mySerial.write("CPE#38");
  mySerial.write(154);
  mySerial.write("      KMUTT     ");
  delay(500);
  mySerial.write(130);
  mySerial.write("CPE#38");
  mySerial.write(154);
  mySerial.write("       KMUTT    ");
  delay(500);
  mySerial.write(130);
  mySerial.write("CPE#38");
  mySerial.write(154);
  mySerial.write("        KMUTT   ");
  delay(500);
  mySerial.write(130);
  mySerial.write("CPE#38");
  mySerial.write(154);
  mySerial.write("         KMUTT  ");
  delay(500);
  mySerial.write(130);
  mySerial.write("CPE#38");
  mySerial.write(154);
  mySerial.write("          KMUTT ");
  delay(500);
  mySerial.write(130);
  mySerial.write("CPE#38");
  mySerial.write(154);
  mySerial.write("           KMUTT");
  delay(500);
  mySerial.write(130);
  mySerial.write("CPE#38");
  mySerial.write(154);
  mySerial.write("T           KMUT");
  delay(500);
  mySerial.write(130);
  mySerial.write("CPE#38");
  mySerial.write(154);
  mySerial.write("TT           KMU");
  delay(500);
  mySerial.write(130);
  mySerial.write("CPE#38");
  mySerial.write(154);
  mySerial.write("UTT           KM");
  delay(500);
  mySerial.write(130);
  mySerial.write("CPE#38");
  mySerial.write(154);
  mySerial.write("MUTT           K");
  delay(500);
}
