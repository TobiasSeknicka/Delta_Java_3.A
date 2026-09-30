package accounts;

import people.Owner;

public class CurrentAccount extends BankAccount {

    public CurrentAccount(Owner owner) {
        super(owner);
    }

    public CurrentAccount(Owner owner, double balance) {
        super(owner, balance);
    }
}
