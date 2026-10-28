import java.util.Scanner;
import mypack.Account;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Packages & Access Modifiers (User Input) ===");
        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double initialBalance = sc.nextDouble();
        sc.nextLine(); // consume newline

        System.out.print("Enter Security Code: ");
        String secCode = sc.nextLine();

        // Creating instance of class from external package 'mypack'
        Account acc = new Account(name, initialBalance, secCode);

        // Accessing public member directly
        System.out.println("\n[Public Member] Account Holder: " + acc.holderName);

        // Accessing private member via public getter
        System.out.println("[Private via Getter] Current Balance: Rs." + acc.getBalance());

        // Deposit operation
        System.out.print("\nEnter amount to deposit: ");
        double depositAmount = sc.nextDouble();
        acc.deposit(depositAmount);
        System.out.println("[Private via Getter] Updated Balance: Rs." + acc.getBalance());

        // Display info accessing package and protected fields inside package
        System.out.println("\nAccount Summary from inside package:");
        acc.displayAccountInfo();

        sc.close();
    }
}
