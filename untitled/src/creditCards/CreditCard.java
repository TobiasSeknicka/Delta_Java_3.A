package creditCards;

import people.Owner;
import transfers.Withdraw;

public class CreditCard implements Withdraw {

    private Owner owner;

    private double balance;

    public CreditCard(Owner owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    @Override
    public void setNewBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }
}
