package accounts.serialization;

import accounts.BankAccount;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class BankAccountGsonSerializationService implements BankAccountSerializationService {

    private final BankAccountSerializeFactory bankAccountSerializeFactory;
    private final Gson gson;

    public BankAccountGsonSerializationService(BankAccountSerializeFactory bankAccountSerializeFactory, Gson gson) {
        this.bankAccountSerializeFactory = bankAccountSerializeFactory;
        this.gson = gson;
    }

    @Override
    public String serializeAll(List<BankAccount> bankAccounts) {
        List<BankAccountSerialize> list = new ArrayList<>();
        for (BankAccount bankAccount : bankAccounts) {
            list.add(bankAccountSerializeFactory.createBankAccountSerialize(bankAccount));
        }
        return gson.toJson(list);
    }

    @Override
    public String serialize(BankAccount bankAccount) {
        return gson.toJson(bankAccountSerializeFactory.createBankAccountSerialize(bankAccount));
    }
}