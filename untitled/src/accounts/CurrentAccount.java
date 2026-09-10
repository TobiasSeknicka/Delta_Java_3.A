package accounts;
import people.AccountOwner;

public class CurrentAccount extends BankAccount{


        public CurrentAccount(AccountOwner owner) {
            super(owner);
        }

        public CurrentAccount(AccountOwner owner, double balance) {

            super(owner, balance);
        }

        @Override
        public void sub(double amount) {

            // kontrola poctu vyberu

            super.sub(amount);
        }
    }
