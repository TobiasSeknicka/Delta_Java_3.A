package accounts;

import people.Owner;

public class SavingAccount extends BankAccount implements InterestPoint {

    private static final double INTEREST = 0.005; // 0,5 %

    public SavingAccount(String uuid, String accountNumber, Owner owner) {
        super(uuid, accountNumber, owner);
    }

    public SavingAccount(Owner owner) {
        super(owner);
    }

    public SavingAccount(Owner owner, double balance) {
        super(owner, balance);
    }

    @Override
    public void calculateInterest() {
        double interest = this.balance * INTEREST;

        this.setNewBalance(this.getBalance() + interest);
    }
}
