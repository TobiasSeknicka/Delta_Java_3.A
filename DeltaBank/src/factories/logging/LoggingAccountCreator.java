package factories.logging;

import accounts.BankAccount;
import people.Owner;

public class LoggingAccountCreator<T extends BankAccount> implements AccountCreator<T> {
    private final AccountCreator<T> inner;

    public LoggingAccountCreator(AccountCreator<T> inner) {
        this.inner = inner;
    }

    @Override
    public T createAccount(Owner owner, double balance){
        System.out.println("Vytvarim ucet");
        T account = inner.createAccount(owner, balance);
        System.out.println("Vytvoril jsem ucet");
        return account;
    }
}
