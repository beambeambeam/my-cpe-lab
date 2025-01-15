#include <stdio.h>
#include <string.h>

int main() {
  FILE *fp;
  char file_name[100];
  char file_path[100];
  char name[10][100];
  int score[10];

  // Prompt the user to enter the file name
  printf("Enter a file name to load student scores: ");
  scanf("%s", file_name);

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

  // Initialize min, max, and sum variables
  int min = score[0];
  int max = score[0];
  int sum = 0;

  // Calculate the minimum, maximum, and sum of scores
  for (int j = 0; j < i; j++) {
    if (score[j] < min) {
      min = score[j];
    }
    if (score[j] > max) {
      max = score[j];
    }
    sum += score[j];
  }

  // Calculate the average score
  float avg = (float)sum / i;

  // Print the statistics to the console
  printf("Average score is %.0f\n", avg);
  printf("Minimum score is %d\n", min);
  printf("Maximum score is %d\n", max);

  // Open a file to save the statistics
  fp = fopen("./stat.txt", "w");
  // Write the statistics to the file
  fprintf(fp, "Average score is %.0f\n", avg);
  fprintf(fp, "Minimum score is %d\n", min);
  fprintf(fp, "Maximum score is %d\n", max);
  // Close the file after writing
  fclose(fp);

  // Inform the user that the statistics have been saved
  printf("Save statistics in file stat.txt successfully.\n");
  printf("End of program. Goodbye.");

  return 0;
}
