#include <iostream>

int main(int argc, char const *argv[])
{
  int x{5};
  std::cout << "x is equal to: " << x << std::endl;

  std::cout << "my name is" << std::endl;
  std::cout << "alex" << std::endl;

  std::cout << "x is equal to: " << x << '\n'; // single quoted (by itself) (conventional)
  std::cout << "Yep." << "\n";                 // double quoted (by itself) (unconventional but okay)
  std::cout << "And that's all, folks!\n";     // between double quotes in existing text (conventional)

  std::cout << "Enter a number: "; // ask user for a number

  std::cin >> x; // get number from keyboard and store it in variable x

  int y{};
  std::cin >> y;

  std::cout << "You entered " << x << " and " << y << '\n';

  return 0;
}
