package transfers;

import accounts.BankAccount;
import accounts.BusinessAccount;
import accounts.StudentAccount;
import notifiers.ConsoleNotifier;
import notifiers.Notifier;

public class AccountTransferService {

    Notifier notifier = new ConsoleNotifier();

    public void transfer(BankAccount from, BankAccount to, double amount) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Account must not be null");
        }

        if (from == to) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        double fee = 0;

        if (from instanceof BusinessAccount) {
            fee = amount * 0.003;
        }

        double newBalance = from.getBalance() - amount - fee;

        double limit = 0;

        if (from instanceof StudentAccount) {
            limit = -5000;
        }

        if (newBalance < limit) {
            throw new RuntimeException("Not enough money");
        }

        from.setNewBalance(newBalance);
        to.setNewBalance(to.getBalance() + amount);

        this.notifier.notify("Transferred " + amount + ", fee " + fee);
    }
}
