package factories;

import accounts.StudentAccount;
import generators.AccountNumberGenerator;
import people.Owner;

public class StudentAccountFactory {

    private final AccountNumberGenerator generator;

    public StudentAccountFactory(AccountNumberGenerator generator) {
        this.generator = generator;
    }

    public StudentAccount createAccount(Owner owner, double balance) {
        StudentAccount studentAccount = new StudentAccount(owner, balance);
        studentAccount.setAccountNumber(generator.generateAccountNumber());
        return studentAccount;
    }
}
