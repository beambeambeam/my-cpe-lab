#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
  FILE *fp;
  char file_name[100];
  char file_path[100];
  int score[10];

  printf("Enter a number of students in the class: ");
  int num_students;
  scanf("%d", &num_students);

  // Prompt the user to enter the file name
  printf("Enter a file name to load student scores: ");
  scanf("%s", file_name);

  // Allocate memory for an array of 'num_students' strings, each with a maximum
  // length of 100 characters.
  char(*name)[100] = malloc(num_students * sizeof(*name));
  if (name == NULL) {
    printf("Memory allocation failed\n");
    return 1;
  }

  // Construct the file path by concatenating "./" with the file name
  strcpy(file_path, "./");
  strcat(file_path, file_name);

  // Open the file for reading
  fp = fopen(file_path, "r");

  // Check if the file was opened successfully
  if (fp == NULL) {
    printf("Error opening file %s\n", file_path);
    return 1;
  }

  int i = 0;
  // Read names and scores from the file until EOF
  while (fscanf(fp, "%s %d", name[i], &score[i]) != EOF) {
    printf("Student %d: %s, Score = %d\n", i + 1, name[i], score[i]);
    i++;
  }
  // Close the file after reading
  fclose(fp);

  char ans_name[100];
  int ans_score;
  FILE *output = fopen(file_path, "a");

  while (1) {
    printf("Enter new student name (type 'END' to sort student score and end "
           "the program): ");
    scanf("%s", ans_name);

    if (strcmp(ans_name, "END") == 0) {
      break;
    }

    printf("Enter %s's Score: ", ans_name);
    scanf("%d", &ans_score);

    if (i < 10) {
      strcpy(name[i], ans_name);
      score[i] = ans_score;
      fprintf(output, "\n%s %d", ans_name, ans_score);
      i++;
    } else {
      printf("Maximum number of students reached.\n");
      break;
    }
  }
  fclose(output);

  // Sort the students by score in ascending order
  for (int i = 0; i < 10; i++) {
    for (int j = i + 1; j < 10; j++) {
      if (score[i] > score[j]) {
        int temp = score[i];
        score[i] = score[j];
        score[j] = temp;

        char temp_name[100];
        strcpy(temp_name, name[i]);
        strcpy(name[i], name[j]);
        strcpy(name[j], temp_name);
      }
    }
  }

  // Write the sorted output to the file
  FILE *sorted_output = fopen(file_path, "w");
  for (int i = 0; i < 10; i++) {
    if (strlen(name[i]) > 0) {
      fprintf(sorted_output, "%s %d\n", name[i], score[i]);
    }
  }
  fclose(sorted_output);

  printf("--------------- Sorted students by scores ---------------\n");
  for (int i = 0; i < 10; i++) {
    if (strlen(name[i]) > 0) {
      printf("Student %d: %s, Score = %d\n", i + 1, name[i], score[i]);
    }
  }

  printf("End of program. Goodbye.\n");

  return 0;
}
