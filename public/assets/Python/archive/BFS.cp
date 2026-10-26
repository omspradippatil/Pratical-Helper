#include <stdio.h>
#include <stdlib.h>

int compare(const void *a, const void *b) {
    return (*(int*)a - *(int*)b);
}

int main() {
    int n;

    printf("Enter the number of programs: ");
    if (scanf("%d", &n) != 1 || n <= 0) {
        printf("Error: Please enter a valid positive number.\n");
        return 1;
    }

    int *lengths = (int*)malloc(n * sizeof(int));
    if (lengths == NULL) {
        printf("Error: Memory allocation failed.\n");
        return 1;
    }

    printf("Enter the lengths of the %d programs separated by spaces: ", n);
    for (int i = 0; i < n; i++) {
        if (scanf("%d", &lengths[i]) != 1 || lengths[i] < 0) {
            printf("Error: Please enter valid positive integers.\n");
            free(lengths);
            return 1;
        }
    }

    qsort(lengths, n, sizeof(int), compare);

    printf("\nOptimal ordering of program lengths: ");
    for (int i = 0; i < n; i++) {
        printf("%d ", lengths[i]);
    }
    printf("\n");

    int total_retrieval_time = 0;
    int current_prefix_sum = 0;

    printf("\nBreakdown of Retrieval Times:\n");
    for (int i = 0; i < n; i++) {
        current_prefix_sum += lengths[i];
        total_retrieval_time += current_prefix_sum;
        printf("  Program %d (Length %d): Total wait time = %d\n", i + 1, lengths[i], current_prefix_sum);
    }

    double mrt = (double)total_retrieval_time / n;

    printf("\nTotal Retrieval Time (Sum): %d\n", total_retrieval_time);
    printf("Mean Retrieval Time (MRT): %.2f\n", mrt);

    free(lengths);

    return 0;
}




#Out put
Enter the number of programs: 10
Enter the lengths of the 10 programs separated by spaces: 9 2 6 1 3 5 4 7 8 9

Optimal ordering of program lengths: 1 2 3 4 5 6 7 8 9 9 

Breakdown of Retrieval Times:
  Program 1 (Length 1): Total wait time = 1
  Program 2 (Length 2): Total wait time = 3
  Program 3 (Length 3): Total wait time = 6
  Program 4 (Length 4): Total wait time = 10
  Program 5 (Length 5): Total wait time = 15
  Program 6 (Length 6): Total wait time = 21
  Program 7 (Length 7): Total wait time = 28
  Program 8 (Length 8): Total wait time = 36
  Program 9 (Length 9): Total wait time = 45
  Program 10 (Length 9): Total wait time = 54

Total Retrieval Time (Sum): 219
Mean Retrieval Time (MRT): 21.90

   
