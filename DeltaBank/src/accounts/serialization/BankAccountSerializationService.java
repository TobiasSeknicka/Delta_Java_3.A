package accounts.serialization;

import accounts.BankAccount;

import java.util.List;

public interface BankAccountSerializationService {
    public String serialize(BankAccount bankAccount);
    public String serializeAll(List<BankAccount> bankAccounts);
}
