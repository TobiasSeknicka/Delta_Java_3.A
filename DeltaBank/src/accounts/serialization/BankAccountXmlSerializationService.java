package accounts.serialization;

import accounts.BankAccount;

import java.util.List;

public class BankAccountXmlSerializationService implements BankAccountSerializationService{
    BankAccountSerializeFactory bankAccountSerializeFactory = new BankAccountSerializeFactory();

    @Override
    public String serializeAll(List<BankAccount> bankAccounts) {
        StringBuilder builder = new StringBuilder();
        builder.append("<accounts>");

        for (BankAccount bankAccount : bankAccounts) {
            builder.append(serialize(bankAccount));
        }
        builder.append("</accounts>");

        return builder.toString();
    }

    @Override
    public String serialize(BankAccount bankAccount) {
        BankAccountSerialize data = bankAccountSerializeFactory.createBankAccountSerialize(bankAccount);

        StringBuilder builder = new StringBuilder();

        builder.append("<account>");
        builder.append("<accountNumber>").append(data.bankAccountNumber).append("</accountNumber>");
        builder.append("<type>").append(data.type).append("</type>");
        builder.append("<balance>").append(data.balance).append("</balance>");
        builder.append("<owner>");
        builder.append("<name>").append(data.ownerName).append("</name>");
        builder.append("<lastName>").append(data.ownerLastName).append("</lastName>");
        builder.append("</owner>");
        builder.append("</account>");

        return builder.toString();
    }
}
