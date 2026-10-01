package factories;

import accounts.StudentAccount;
import generators.AccountNumberGenerator;
import people.Owner;

public class StudentAccountFactory {

    AccountNumberGenerator generator = new AccountNumberGenerator();
    public StudentAccount createAccount(Owner owner, double balance) {
        StudentAccount studentAccount = new StudentAccount(owner, balance);
        studentAccount.setAccountNumber(generator.generateAccountNumber());
        return studentAccount;
    }
}
