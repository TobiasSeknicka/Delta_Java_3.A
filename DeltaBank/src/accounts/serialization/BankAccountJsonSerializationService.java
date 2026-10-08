package accounts.serialization;

import accounts.BankAccount;

import java.util.List;

public class BankAccountJsonSerializationService implements BankAccountSerializationService{

    BankAccountSerializeFactory bankAccountSerializeFactory = new BankAccountSerializeFactory();

    @Override
    public String serializeAll(List<BankAccount> bankAccounts){
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        for (int i = 0; i<bankAccounts.size(); i++){
            builder.append(serialize(bankAccounts.get(i)));

            if(i != bankAccounts.size()-1){
                builder.append(",");
            }
        }

        builder.append("]");

        return  builder.toString();
    }

    @Override
    public String serialize(BankAccount bankAccount){
        BankAccountSerialize data = bankAccountSerializeFactory.createBankAccountSerialize(bankAccount);

        StringBuilder builder = new StringBuilder();

        builder.append("{");
        builder.append("\"accountNumber\":\"").append(data.bankAccountNumber).append("\",");
        builder.append("\"type\":\"").append(data.type).append("\",");
        builder.append("\"balance\":").append(data.balance).append(",");
        builder.append("\"owner\":{");
        builder.append("\"name\":\"").append(data.ownerName).append("\",");
        builder.append("\"lastName\":\"").append(data.ownerLastName).append("\"");
        builder.append("}");
        builder.append("}");

        return builder.toString();
    }
}
