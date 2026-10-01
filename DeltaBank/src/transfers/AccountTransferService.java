package transfers;

import accounts.BankAccount;
import accounts.BusinessAccount;
import accounts.StudentAccount;
import notifiers.ConsoleNotifier;
import notifiers.Notifier;

public class AccountTransferService {

    private static final double BUSINESS_ACCOUNT_TRANSFER_FEE = 0.003;
    private static final double STUDENT_ACCOUNT_LIMIT = -5000;

    Notifier notifier = new ConsoleNotifier();

    public void transfer(BankAccount from, BankAccount to, double amount) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Account must not be null");
        }

        if (from == to) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (Double.isNaN(amount) || Double.isInfinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Amount must be a positive number");
        }

        double fee = 0;

        if (from instanceof BusinessAccount) {
            fee = amount * BUSINESS_ACCOUNT_TRANSFER_FEE;
        }

        double newFromBalance = from.getBalance() - amount - fee;

        if (newFromBalance < this.getLimit(from)) {
            throw new RuntimeException("Insufficient funds for transfer");
        }

        from.setNewBalance(newFromBalance);
        to.setNewBalance(to.getBalance() + amount);

        this.notifier.notify("Transferred " + amount + " (fee " + fee + ")");
    }

    private double getLimit(BankAccount account) {

        if (account instanceof StudentAccount) {
            return STUDENT_ACCOUNT_LIMIT;
        }

        return 0;
    }
}
