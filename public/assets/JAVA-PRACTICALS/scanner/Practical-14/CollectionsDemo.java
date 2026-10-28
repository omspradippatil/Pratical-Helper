import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.HashSet;
import java.util.Scanner;

public class CollectionsDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Java Collections Framework (User Input) ===");

        // 1. ArrayList
        ArrayList<String> arrayList = new ArrayList<>();
        System.out.println("\n--- 1. ArrayList ---");
        System.out.print("Enter number of programming languages to add to ArrayList: ");
        int nList = sc.nextInt();
        for (int i = 0; i < nList; i++) {
            System.out.print("Language " + (i + 1) + ": ");
            arrayList.add(sc.next());
        }
        System.out.println("ArrayList contents: " + arrayList);

        // 2. LinkedList
        LinkedList<String> linkedList = new LinkedList<>();
        System.out.println("\n--- 2. LinkedList ---");
        System.out.print("Enter middle element for LinkedList: ");
        linkedList.add(sc.next());
        System.out.print("Enter element to insert at the FRONT (addFirst): ");
        linkedList.addFirst(sc.next());
        System.out.print("Enter element to insert at the END (addLast): ");
        linkedList.addLast(sc.next());
        System.out.println("LinkedList contents: " + linkedList);

        // 3. Vector
        Vector<Integer> vector = new Vector<>();
        System.out.println("\n--- 3. Vector (Synchronized List) ---");
        System.out.print("Enter 3 integers for Vector: ");
        for (int i = 0; i < 3; i++) {
            vector.add(sc.nextInt());
        }
        System.out.println("Vector contents: " + vector);

        // 4. HashSet
        HashSet<String> hashSet = new HashSet<>();
        System.out.println("\n--- 4. HashSet (Unique Elements Demo) ---");
        System.out.println("Enter 4 colors (feel free to repeat one to verify duplicate elimination):");
        for (int i = 0; i < 4; i++) {
            System.out.print("Color " + (i + 1) + ": ");
            hashSet.add(sc.next());
        }
        System.out.println("HashSet contents (duplicates automatically removed): " + hashSet);

        sc.close();
    }
}
