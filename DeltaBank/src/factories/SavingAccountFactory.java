package factories;

import accounts.SavingAccount;
import generators.AccountNumberGenerator;
import people.Owner;

public class SavingAccountFactory {

    AccountNumberGenerator generator = new AccountNumberGenerator();
    public SavingAccount createAccount(Owner owner, double balance) {
        SavingAccount savingAccount = new SavingAccount(owner, balance);
        savingAccount.setAccountNumber(generator.generateAccountNumber());
        return savingAccount;
    }
}
