package transfers;

import accounts.BankAccount;
import accounts.BusinessAccount;
import factories.TransactionFactory;
import notifiers.Notifier;

public class AccountTransferService {

    private static final double BUSINESS_ACCOUNT_TRANSFER_FEE = 0.003;

    private final Notifier notifier;
    private final TransferLoggerService logger;
    private final TransferService transferService;
    private final TransactionFactory transactionFactory;

    public AccountTransferService(TransferLoggerService logger,
                                  TransferService transferService,
                                  Notifier notifier,
                                  TransactionFactory transactionFactory) {
        this.logger = logger;
        this.transferService = transferService;
        this.notifier = notifier;
        this.transactionFactory = transactionFactory;
    }

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
            fee = amount * BUSINESS_ACCOUNT_TRANSFER_FEE;
        }

        double newBalance = from.getBalance() - amount - fee;

        if (newBalance < transferService.getWithdrawLimit(from)) {
            throw new RuntimeException("Not enough money");
        }

        from.setNewBalance(newBalance);
        to.setNewBalance(to.getBalance() + amount);

        this.notifier.notify("Transferred " + amount + ", fee " + fee);

        logger.log(transactionFactory.createTransfer(from, to, amount, fee));
    }
}
