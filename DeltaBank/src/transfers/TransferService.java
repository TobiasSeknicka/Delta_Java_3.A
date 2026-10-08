package transfers;

import accounts.BusinessAccount;
import accounts.SavingAccount;
import accounts.StudentAccount;
import factories.TransactionFactory;
import notifiers.ConsoleNotifier;
import notifiers.Notifier;

public class TransferService {

    private static final double BUSINESS_ACCOUNT_SUB_FEE = 0.01;       // 1 % z kazdeho vyberu
    private static final double SAVING_ACCOUNT_ADD_BONUS = 0.005;      // 0,5 % pri vkladu
    private static final double STUDENT_ACCOUNT_LIMIT = -5000;

    Notifier notifier = new ConsoleNotifier();

    private TransferLoggerService logger;
    private TransactionFactory transactionFactory = new TransactionFactory();

    public TransferService(TransferLoggerService logger) {
        this.logger = logger;
    }

    public void withdraw(Withdraw withdrawObject, double amount) {
        this.validateObject(withdrawObject);
        this.validateAmount(amount);

        this.notifier.notify("Sub amount is " + amount);

        double fee = 0;

        if (withdrawObject instanceof BusinessAccount) {
            fee = amount * BUSINESS_ACCOUNT_SUB_FEE;
        }

        double newBalance = withdrawObject.getBalance() - amount - fee;

        if (newBalance < this.getWithdrawLimit(withdrawObject)) {
            throw new RuntimeException("Withdrawal limit reached");
        }

        withdrawObject.setNewBalance(newBalance);

        logger.log(transactionFactory.createWithdraw(withdrawObject, amount, fee));
    }

    public void addToBalance(Withdraw withdrawObject, double amount) {
        this.validateObject(withdrawObject);
        this.validateAmount(amount);

        if (withdrawObject instanceof SavingAccount) {
            amount += amount * SAVING_ACCOUNT_ADD_BONUS;
        }

        this.notifier.notify("Add amount is " + amount);

        withdrawObject.setNewBalance(withdrawObject.getBalance() + amount);

        logger.log(transactionFactory.createDeposit(withdrawObject, amount));
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

    public double getWithdrawLimit(Withdraw withdrawObject) {

        if (withdrawObject instanceof StudentAccount) {
            return STUDENT_ACCOUNT_LIMIT;
        }

        return 0;
    }
}
