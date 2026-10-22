package factories.logging;

import accounts.BankAccount;
import people.Owner;

public interface AccountCreator<T extends BankAccount> {
    T createAccount(Owner owner, double balance);
}
