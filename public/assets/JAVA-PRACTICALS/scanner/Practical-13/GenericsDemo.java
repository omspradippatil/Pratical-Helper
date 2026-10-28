import java.util.Scanner;

class Box<T> {
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}

public class GenericsDemo {
    // Generic method to print any array of reference types
    public static <E> void printArray(E[] array) {
        for (E element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Generic Classes & Generic Methods (User Input) ===");

        // 1. Generic Class Box<T> with Integer
        System.out.print("Enter an integer value to store in Box<Integer>: ");
        int userInt = sc.nextInt();
        Box<Integer> intBox = new Box<>();
        intBox.setItem(userInt);
        System.out.println("Box<Integer> contains: " + intBox.getItem());
        sc.nextLine(); // consume newline

        // 2. Generic Class Box<T> with String
        System.out.print("\nEnter a text value to store in Box<String>: ");
        String userStr = sc.nextLine();
        Box<String> strBox = new Box<>();
        strBox.setItem(userStr);
        System.out.println("Box<String> contains: " + strBox.getItem());

        // 3. Generic Method with String Array
        System.out.print("\nHow many words in your String array? ");
        int count = sc.nextInt();
        String[] words = new String[count];
        System.out.println("Enter " + count + " words:");
        for (int i = 0; i < count; i++) {
            words[i] = sc.next();
        }

        System.out.print("\nPrinting array via generic method <E> printArray: ");
        printArray(words);

        sc.close();
    }
}
