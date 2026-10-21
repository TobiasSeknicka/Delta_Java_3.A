package accounts.serialization;

import accounts.BankAccount;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class BankAccountGsonSerializationService implements BankAccountSerializationService {

    BankAccountSerializeFactory bankAccountSerializeFactory = new BankAccountSerializeFactory();
    Gson gson = new Gson();

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