package mypack;

public class Account {
    public String holderName;      // Public: accessible everywhere
    private double balance;         // Private: accessible only in Account class
    protected String securityCode;  // Protected: accessible within package & subclasses
    String accountType;             // Default (package-private): accessible within mypack

    public Account(String holderName, double balance, String securityCode) {
        this.holderName = holderName;
        this.balance = balance;
        this.securityCode = securityCode;
        this.accountType = "Savings";
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Rs." + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void displayAccountInfo() {
        System.out.println("Account Type (default package-private): " + accountType);
        System.out.println("Security Code (protected member): " + securityCode);
    }
}
