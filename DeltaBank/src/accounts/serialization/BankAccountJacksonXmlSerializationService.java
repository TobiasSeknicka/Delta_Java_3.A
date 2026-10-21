package accounts.serialization;

import accounts.BankAccount;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.util.ArrayList;
import java.util.List;

public class BankAccountJacksonXmlSerializationService implements BankAccountSerializationService {

    BankAccountSerializeFactory bankAccountSerializeFactory = new BankAccountSerializeFactory();
    XmlMapper xmlMapper = new XmlMapper();

    @Override
    public String serializeAll(List<BankAccount> bankAccounts) {
        List<BankAccountSerialize> list = new ArrayList<>();
        for (BankAccount bankAccount : bankAccounts) {
            list.add(bankAccountSerializeFactory.createBankAccountSerialize(bankAccount));
        }
        try {
            return xmlMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String serialize(BankAccount bankAccount) {
        try {
            return xmlMapper.writeValueAsString(bankAccountSerializeFactory.createBankAccountSerialize(bankAccount));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
