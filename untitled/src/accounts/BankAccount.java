package accounts;

import people.AccountOwner;

public abstract class BankAccount {

    private String uuid;

    private String accountNumber; // 2102405518

    private AccountOwner owner;

    private double balance;

    public BankAccount(AccountOwner owner) {
        this.owner = owner;
        this.balance = 0;
    }

    public BankAccount(AccountOwner owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void sub(double amount) {
        System.out.println("Sub amount is " + amount);

        double newBalance = balance - amount;

        if (newBalance < 0) {
            throw new RuntimeException("Balance is negative");
        }

        this.balance = newBalance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public void add(double amount) {
        System.out.println("Add amount is " + amount);

        this.balance = this.balance + amount;
    }
}

