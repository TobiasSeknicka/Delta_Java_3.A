package accounts;

import notifiers.EmailNotifier;
import notifiers.Notifier;
import people.Owner;
import transfers.Withdraw;

public abstract class BankAccount implements Withdraw {

    private String uuid;

    private String accountNumber; // 2102405518

    private Owner owner;

    protected double balance;

    protected Notifier notifier = new EmailNotifier();

    public BankAccount(String uuid, String accountNumber, Owner owner) {
        this.uuid = uuid;
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = 0;
    }

    public BankAccount(Owner owner) {
        this.owner = owner;
        this.balance = 0;
    }

    public BankAccount(Owner owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setNewBalance(double balance) {
        this.balance = balance;
    }
}
