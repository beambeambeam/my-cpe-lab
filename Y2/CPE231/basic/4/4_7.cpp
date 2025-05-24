#include <iostream>
#include <cstdio> // Required for printf

int main()
{
  // Define numbers using scientific notation
  double num1 = 1.2e4;         // Represents 1.2 x 10^4
  double num2 = 5.9722e24;     // Represents 5.9722 x 10^24 (mass of Earth example)
  double num3 = 5e-2;          // Represents 5 x 10^-2 (0.05)
  double num4 = 9.1093837e-31; // Mass of an electron example

  std::cout << "Demonstrating numbers defined in scientific notation:" << std::endl;
  std::cout << "-----------------------------------------------------" << std::endl;

  // Print numbers in default format
  printf("num1 (1.2e4) in default format: %.35f\n", num1);
  printf("num2 (5.9722e24) in default format: %.35f\n", num2);
  printf("num3 (5e-2) in default format: %.35f\n", num3);
  printf("num4 (9.1093837e-31) in default format: %.35f\n", num4);
  std::cout << std::endl;

  // Print the numbers in scientific notation format using printf
  std::cout << "Printing numbers explicitly in scientific notation format:" << std::endl;
  std::cout << "---------------------------------------------------------" << std::endl;
  printf("num1 (1.2e4) in scientific format (precision 1): %.1e\n", num1);
  printf("num2 (5.9722e24) in scientific format (precision 4): %.4e\n", num2);
  printf("num3 (5e-2) in scientific format (precision 1): %.1e\n", num3); // Will show as 5.0e-02
  printf("num4 (9.1093837e-31) in scientific format (precision 7): %.7e\n", num4);
  std::cout << std::endl;

  // Demonstrate converting a decimal to scientific
  double decimal_val1 = 34.50;
  std::cout << "Demonstrating formatting a decimal number into scientific notation:" << std::endl;
  std::cout << "--------------------------------------------------------------------" << std::endl;
  printf("Decimal value %.2f in scientific notation (3 digits after decimal for significand): %.3e\n", decimal_val1, decimal_val1);

  double decimal_val2 = 0.004000;
  printf("Decimal value %.6f in scientific notation (3 digits after decimal for significand): %.3e\n", decimal_val2, decimal_val2);

  std::cout << std::endl
            << "Program finished." << std::endl;

  return 0;
}
