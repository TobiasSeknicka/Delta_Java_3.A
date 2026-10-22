package factories.logging;

import accounts.BankAccount;
import people.Owner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimedAccountCreator<T extends BankAccount> implements AccountCreator<T> {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    private final AccountCreator<T> inner;

    public TimedAccountCreator(AccountCreator<T> inner) {
        this.inner = inner;
    }

    @Override
    public T createAccount(Owner owner, double balance){
        System.out.println("Vytvarim ucet " + LocalDateTime.now().format(FORMAT));
        T account = inner.createAccount(owner, balance);
        System.out.println("Vytvoril jsem ucet " + LocalDateTime.now().format(FORMAT));
        return account;
    }

}
