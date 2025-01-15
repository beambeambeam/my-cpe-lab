#include <stdio.h>

#define REP(i, a, b) for (int i = a; i <= b; i++)

int main(int argc, char const *argv[]) {
  int nums, min = __INT_MAX__, max = -9999;
  int pos = 0, min_pos, max_pos;

  while (1) {
    scanf("%d", &nums);
    pos++;

    if (nums == -9999) {
      break;
    }

    if (min > nums) {
      min = nums;
      min_pos = pos;
    } else if (max < nums) {
      max = nums;
      max_pos = pos;
    }
  }

  printf("%d %d\n", max, max_pos);
  printf("%d %d\n", min, min_pos);

  return 0;
}
