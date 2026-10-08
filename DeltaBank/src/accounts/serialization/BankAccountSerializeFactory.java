package accounts.serialization;

import accounts.BankAccount;

public class BankAccountSerializeFactory {
    public BankAccountSerialize createBankAccountSerialize(BankAccount bankAccount) {
        BankAccountSerialize bankAccountSerialize = new BankAccountSerialize();

        bankAccountSerialize.bankAccountNumber = bankAccount.getAccountNumber();
        bankAccountSerialize.type = bankAccount.getClass().getSimpleName();
        bankAccountSerialize.balance = bankAccount.getBalance();
        bankAccountSerialize.ownerName = bankAccount.getOwner().getName();
        bankAccountSerialize.ownerLastName = bankAccount.getOwner().getLastName();

        return bankAccountSerialize;
    }
}