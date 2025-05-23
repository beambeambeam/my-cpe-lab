#include <iostream>

int main(int argc, char const *argv[])
{
  double w1{4.5};

  // int w1{4.5}; // error can't convert in init

  int w2 = 4.5; // got warning insted of error

  int w3{}; // will be 0 init

  int height = {5};

  std::cout << w1 << " " << w2 << " " << w3 << " " << height << "\n";

  int x{5};

  double pi{3.14159};
  [[maybe_unused]] double gravity{9.8};
  double phi{1.61803};

  std::cout << pi << '\n';  // pi is used
  std::cout << phi << '\n'; // phi is used

  return 0;
}
