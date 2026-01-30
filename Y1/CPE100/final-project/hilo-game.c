#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void display_start();
int win_choice_by_nums(int sums_roll);
int option_for_bet();
int enter_bet(int balance, int choice);
char *get_choice_by_value(int choice);
void clear_console();
int random_dice();

int money = 1000;

typedef struct {
  int choice;
  int bet_amount;
  int status;
} betlog;

betlog Betlogs[99];

int Round[99];

int main() {
  clear_console();
  display_start();
  int bet_times = 0;
  int round_times = 0;

  while (1) {
    while (1) {
      printf("\n");
      printf("-->Betting phase<--\n");
      int choice = option_for_bet();
      if (choice == 4) {
        break;
      }

      int bet_amount = enter_bet(money, choice);
      betlog Betlog;
      Betlog.bet_amount = bet_amount;
      Betlog.choice = choice;
      Betlogs[bet_times] = Betlog;
      bet_times++;

      printf("Your betting is recorded.\n");
    }

    printf("Betting phase is ended. Below is the summary of your betting\n");
    int bet_index[3] = {0, 0, 0};
    for (int i = 0; i < bet_times; i++) {
      bet_index[Betlogs[i].choice - 1] += Betlogs[i].bet_amount;
    }

    printf("1. Hi (x1) %d Bath\n", bet_index[0]);
    printf("2. LO (x1) %d Bath\n", bet_index[1]);
    printf("3. 11 Hi-Lo (x7) %d Bath\n", bet_index[2]);

    printf("\n");
    printf("-->Rolling phase<--\n");
    int roll[3] = {random_dice(), random_dice(), random_dice()};
    printf("The rolling result is %d %d %d\n", roll[0], roll[1], roll[2]);

    int sums_roll = roll[0] + roll[1] + roll[2];

    printf("The summation is %d. Please see your betting result below\n",
           sums_roll);

    int win_choice = win_choice_by_nums(sums_roll);

    int round_money = 0;

    for (size_t i = 0; i < 3; i++) {
      if (i == win_choice - 1) {
        int factor = 1;
        if (i == 2) {
          factor = 7;
        }
        round_money += bet_index[i] * factor;

        printf("%s %d Bath -> You win %d Bath.\n", get_choice_by_value(i + 1),
               bet_index[i], bet_index[i] * factor);
        continue;
      } else if (bet_index[i] == 0) {
        printf("%s %d Bath -> You get nothing.\n", get_choice_by_value(i + 1),
               bet_index[i]);
        continue;
      } else {
        round_money -= bet_index[i];
        printf("%s %d Bath -> You lose %d Bath.\n", get_choice_by_value(i + 1),
               bet_index[i], bet_index[i]);
      }
    }

    money += round_money;

    if (round_money < 0) {
      printf("In summary you lose %d Bath this round, Now you have %d in your "
             "pocket",
             round_money, money);
    } else if (round_money > 0) {
      printf("In summary you win %d Bath this round, Now you have %d in your "
             "pocket",
             round_money, money);
    } else {
      printf(
          "In summary you get nothing %d Bath this round, Now you have %d in "
          "your pocket",
          round_money, money);
    }

    Round[round_times] = round_money;

    round_times++;

    printf("\n");
    printf("\n");
    printf("Do you want to play again (y/n)? ");
    char leave_choice;
    scanf("%s", &leave_choice);

    if (leave_choice == 'n') {
      break;
    }
  }

  printf("\n");
  printf("=======================\n");
  printf("Summary\n");
  for (int i = 0; i < round_times; i++) {
    if (Round[i] > 0) {
      printf("Round %d: Win %d Bath\n", i + 1, Round[i]);
    } else if (Round[i] == 0) {
      printf("Round %d: You do nothing with %d Bath\n", i + 1, Round[i]);
    } else {
      printf("Round %d: Loss %d Bath\n", i + 1, Round[i] * -1);
    }
  }

  if (money > 0) {
    printf("Please get your %d Baht before leaveing.", money);
  } else {
    printf("Please pay your debt %d before leaveing.", money);
  }

  return 0;
}

void display_start() {
  printf("************Welcome to Hi-Lo Forever************\n");
  printf("Let's start Playing. Now you have %d Baht as your pocket Money\n",
         money);
}

int option_for_bet() {
  int choice;
  while (1) {
    printf("1. Hi (x1)\n");
    printf("2. LO (x1)\n");
    printf("3. 11 Hi-Lo (x7)\n");
    printf("4. Fisnish bettings phase\n");

    printf("Select an option: ");
    scanf("%d", &choice);

    if (choice == 1 || choice == 2 || choice == 3 || choice == 4) {
      break;
    }

    printf("something went wrong");
  }
  return choice;
}

int enter_bet(int balance, int choice) {

  while (1) {
    int bet;
    printf("Enter betting amount for option %d: ", choice);
    scanf("%d", &bet);

    if (bet > 0) {
      return bet;
    }

    printf("something went wrong with amount");
  }
}

char *get_choice_by_value(int choice) {
  switch (choice) {
  case 1:
    return "Hi (x1)";
  case 2:
    return "Lo (x1)";
  case 3:
    return "11 Hi-Lo (x7)";

  default:
    return "";
  }

  return "";
}

void clear_console() {
  printf("\033[H\033[J");
  return;
}

int random_dice() {
  int nums;
  srand(nums);
  nums = (nums + time(NULL)) % 6 + 1;

  if (nums < 0) {
    return nums * -1;
  }

  return nums;
}

int win_choice_by_nums(int sums_roll) {
  if (sums_roll < 11) {
    return 2;
  }

  if (sums_roll > 11) {
    return 1;
  }

  return 3;
}
