#include <iostream>

int main(int argc, char const *argv[])
{
  std::cout << 2 + 3 << '\n';

  int x{6};
  int y{x - 2};
  std::cout << y << '\n';

  int z{};
  z = x;
  std::cout << z * x << '\n';

  return 0;
}
