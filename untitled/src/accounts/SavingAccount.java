package accounts;

import people.AccountOwner;

public class SavingAccount extends BankAccount{

    public SavingAccount(AccountOwner owner) {
        super(owner);
    }

    public SavingAccount(AccountOwner owner, double balance) {

        super(owner, balance);
    }

    @Override
    public void add(double amount) {
        System.out.println("Add amount is " + (amount+(amount*0.005)));

        super.add(amount+(amount*0.005));
    }}
