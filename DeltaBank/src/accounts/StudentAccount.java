package accounts;

import people.Owner;

public class StudentAccount extends BankAccount {

    private String school;

    public StudentAccount(Owner owner, String school) {
        super(owner);

        this.school = school;
    }

    public StudentAccount(Owner owner, double balance) {
        super(owner, balance);
    }

    public String getSchool() {
        return this.school;
    }
}
