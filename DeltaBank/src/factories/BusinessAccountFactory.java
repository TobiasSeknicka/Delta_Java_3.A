package factories;

import accounts.BusinessAccount;
import generators.AccountNumberGenerator;
import people.Owner;

public class BusinessAccountFactory {

    AccountNumberGenerator generator = new AccountNumberGenerator();
    public BusinessAccount createAccount(Owner owner, double balance) {
        BusinessAccount businessAccount= new BusinessAccount(owner, balance);
        businessAccount.setAccountNumber(generator.generateAccountNumber());
        return businessAccount;
    }
}
