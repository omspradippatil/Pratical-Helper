package mypack;

public class Account {
    public String holderName;
    private double balance;
    protected String securityCode;
    String accountType;

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
        }
    }

    public void displayAccountInfo() {
        System.out.println("Account Type: " + accountType);
        System.out.println("Security Code: " + securityCode);
    }
}
