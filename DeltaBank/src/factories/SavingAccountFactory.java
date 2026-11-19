package factories;

import accounts.SavingAccount;
import generators.AccountNumberGenerator;
import people.Owner;

public class SavingAccountFactory {

    private final AccountNumberGenerator generator;

    public SavingAccountFactory(AccountNumberGenerator generator) {
        this.generator = generator;
    }

    public SavingAccount createAccount(Owner owner, double balance) {
        SavingAccount savingAccount = new SavingAccount(owner, balance);
        savingAccount.setAccountNumber(generator.generateAccountNumber());
        return savingAccount;
    }
}
