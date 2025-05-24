#include <iomanip> // for output manipulator std::setprecision()
#include <iostream>

int main()
{
  std::cout << 5.0 << '\n';
  std::cout << 6.7f << '\n';
  std::cout << 9876543.21 << '\n';
  std::cout << 9.87654321f << '\n';
  std::cout << 987.654321f << '\n';
  std::cout << 987654.321f << '\n';
  std::cout << 9876543.21f << '\n';
  std::cout << 0.0000987654321f << '\n';

  std::cout << std::setprecision(17);                             // show 17 digits of precision
  std::cout << 3.33333333333333333333333333333333333333f << '\n'; // f suffix means float
  std::cout << 3.33333333333333333333333333333333333333 << '\n';  // no suffix means double

  float f{123456789.0f};             // f has 10 significant digits
  std::cout << std::setprecision(9); // to show 9 digits in f
  std::cout << f << '\n';

  double zero{0.0};

  double posinf{5.0 / zero}; // positive infinity
  std::cout << posinf << '\n';

  double neginf{-5.0 / zero}; // negative infinity
  std::cout << neginf << '\n';

  double z1{0.0 / posinf}; // positive zero
  std::cout << z1 << '\n';

  double z2{-0.0 / posinf}; // negative zero
  std::cout << z2 << '\n';

  double nan{zero / zero}; // not a number (mathematically invalid)

  return 0;
}
