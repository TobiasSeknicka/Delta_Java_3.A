package factories;

import accounts.CurrentAccount;
import generators.AccountNumberGenerator;
import people.Owner;

public class CurrentAccountFactory {

    AccountNumberGenerator generator = new AccountNumberGenerator();
    public CurrentAccount createAccount(Owner owner, double balance) {
        CurrentAccount currentAccount = new CurrentAccount(owner, balance);
        currentAccount.setAccountNumber(generator.generateAccountNumber());
        return currentAccount;
    }
}
