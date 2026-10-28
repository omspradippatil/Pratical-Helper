import mypack.Account;

public class Main {
    public static void main(String[] args) {
        Account acc = new Account("Omkar", 25000.0, "SEC-9841");

        System.out.println("Account Holder: " + acc.holderName);
        System.out.println("Initial Balance: " + acc.getBalance());

        acc.deposit(5000.0);
        System.out.println("Updated Balance: " + acc.getBalance());

        acc.displayAccountInfo();
    }
}
