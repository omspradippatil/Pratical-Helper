import java.util.Arrays;

public class ArraySorting {
    public static void main(String[] args) {
        int[] arr1D = {64, 25, 12, 22, 11};
        System.out.print("Original 1D Array: ");
        print1D(arr1D);

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

        int[][] arr2D = {
            {9, 2, 7},
            {4, 8, 1},
            {5, 6, 3}
        };

        System.out.println("Original 2D Array:");
        print2D(arr2D);

        for (int i = 0; i < arr2D.length; i++) {
            Arrays.sort(arr2D[i]);
        }

        System.out.println("Row-wise Sorted 2D Array:");
        print2D(arr2D);
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
