package accounts.services;

import accounts.*;
import factories.logging.AccountCreator;
import people.Owner;

import java.util.ArrayList;
import java.util.List;

public class AccountService {

    private final List<BankAccount> accounts = new ArrayList<>();

    private final AccountCreator<CurrentAccount> currentAccountCreator;
    private final AccountCreator<StudentAccount> studentAccountCreator;
    private final AccountCreator<SavingAccount> savingAccountCreator;
    private final AccountCreator<BusinessAccount> businessAccountCreator;

    public AccountService(AccountCreator<CurrentAccount> currentAccountCreator,
                          AccountCreator<StudentAccount> studentAccountCreator,
                          AccountCreator<SavingAccount> savingAccountCreator,
                          AccountCreator<BusinessAccount> businessAccountCreator) {
        this.currentAccountCreator = currentAccountCreator;
        this.studentAccountCreator = studentAccountCreator;
        this.savingAccountCreator = savingAccountCreator;
        this.businessAccountCreator = businessAccountCreator;
    }

    public BankAccount createCurrentAccount(Owner owner, double balance) {
        return register(currentAccountCreator.createAccount(owner, balance));
    }

    public BankAccount createStudentAccount(Owner owner, double balance) {
        return register(studentAccountCreator.createAccount(owner, balance));
    }

    public BankAccount createSavingAccount(Owner owner, double balance) {
        return register(savingAccountCreator.createAccount(owner, balance));
    }

    public BankAccount createBusinessAccount(Owner owner, double balance) {
        return register(businessAccountCreator.createAccount(owner, balance));
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    private BankAccount register(BankAccount bankAccount) {
        accounts.add(bankAccount);
        return bankAccount;
    }
}
