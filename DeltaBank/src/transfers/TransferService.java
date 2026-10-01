package transfers;

import accounts.BankAccount;
import accounts.BusinessAccount;
import accounts.SavingAccount;
import accounts.StudentAccount;
import notifiers.ConsoleNotifier;
import notifiers.Notifier;

public class TransferService {

    private static final double BUSINESS_ACCOUNT_SUB_FEE = 0.01;       // 1 % z kazdeho vyberu
    private static final double BUSINESS_ACCOUNT_TRANSFER_FEE = 0.003; // 0,3 % z prevadene castky
    private static final double SAVING_ACCOUNT_ADD_BONUS = 0.005;      // 0,5 % pri vkladu
    private static final double STUDENT_ACCOUNT_LIMIT = -5000;

    Notifier notifier = new ConsoleNotifier();

    public void withdraw(Withdraw withdrawObject, double amount) {
        this.validateObject(withdrawObject);
        this.validateAmount(amount);

        this.notifier.notify("Sub amount is " + amount);

        double newBalance = withdrawObject.getBalance() - amount;

        if (withdrawObject instanceof BusinessAccount) {
            newBalance -= amount * BUSINESS_ACCOUNT_SUB_FEE;
        }

        if (newBalance < this.getWithdrawLimit(withdrawObject)) {
            throw new RuntimeException("Withdrawal limit reached");
        }

        withdrawObject.setNewBalance(newBalance);
    }

    public void addToBalance(Withdraw withdrawObject, double amount) {
        this.validateObject(withdrawObject);
        this.validateAmount(amount);

        if (withdrawObject instanceof SavingAccount) {
            amount += amount * SAVING_ACCOUNT_ADD_BONUS;
        }

        this.notifier.notify("Add amount is " + amount);

        withdrawObject.setNewBalance(withdrawObject.getBalance() + amount);
    }

    public void transfer(BankAccount from, BankAccount to, double amount) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Source and target account must not be null");
        }
        if (from == to) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }
        this.validateAmount(amount);

        double fee = 0;
        if (from instanceof BusinessAccount) {
            fee = amount * BUSINESS_ACCOUNT_TRANSFER_FEE;
        }

        double newFromBalance = from.getBalance() - amount - fee;

        if (newFromBalance < this.getWithdrawLimit(from)) {
            throw new RuntimeException("Insufficient funds for transfer");
        }

        from.setNewBalance(newFromBalance);
        to.setNewBalance(to.getBalance() + amount);

        this.notifier.notify("Transferred " + amount + " (fee " + fee + ")");
    }

    private void validateObject(Withdraw withdrawObject) {
        if (withdrawObject == null) {
            throw new IllegalArgumentException("Account must not be null");
        }
    }

    private void validateAmount(double amount) {
        if (Double.isNaN(amount) || Double.isInfinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Amount must be a positive number");
        }
    }

    private double getWithdrawLimit(Withdraw withdrawObject) {

        if (withdrawObject instanceof StudentAccount) {
            return STUDENT_ACCOUNT_LIMIT;
        }

        return 0;
    }
}
