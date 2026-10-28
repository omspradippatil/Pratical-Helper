import java.util.Arrays;
import java.util.Scanner;

public class ArraySorting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== 1D and 2D Array Sorting (User Input) ===");

        // 1D Array Sorting
        System.out.print("Enter size of 1D array: ");
        int n = sc.nextInt();
        int[] arr1D = new int[n];

        System.out.println("Enter " + n + " elements for 1D array:");
        for (int i = 0; i < n; i++) {
            arr1D[i] = sc.nextInt();
        }

        System.out.print("Original 1D Array: ");
        print1D(arr1D);

        // Selection Sort on 1D array
        for (int i = 0; i < arr1D.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr1D.length; j++) {
                if (arr1D[j] < arr1D[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr1D[minIdx];
            arr1D[minIdx] = arr1D[i];
            arr1D[i] = temp;
        }

        System.out.print("Sorted 1D Array (Selection Sort): ");
        print1D(arr1D);

        // 2D Array Sorting
        System.out.print("\nEnter rows for 2D array (matrix): ");
        int rows = sc.nextInt();
        System.out.print("Enter columns for 2D array (matrix): ");
        int cols = sc.nextInt();

        int[][] arr2D = new int[rows][cols];
        System.out.println("Enter matrix elements (" + (rows * cols) + " integers):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arr2D[i][j] = sc.nextInt();
            }
        }

        System.out.println("\nOriginal 2D Array:");
        print2D(arr2D);

        // Row-wise sorting of 2D array
        for (int i = 0; i < arr2D.length; i++) {
            Arrays.sort(arr2D[i]);
        }

        System.out.println("Row-wise Sorted 2D Array:");
        print2D(arr2D);

        sc.close();
    }

    public static void print1D(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static void print2D(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
