package factories;

import accounts.BankAccount;
import transactions.Transaction;
import transactions.TransactionType;
import transfers.Withdraw;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionFactory {
    public Transaction createDeposit(Withdraw to, double amount) {
        return create(TransactionType.DEPOSIT, null, getIdentifier(to), amount, 0);
    }

    public Transaction createWithdraw(Withdraw from, double amount, double fee) {
        return create(TransactionType.WITHDRAW, getIdentifier(from), null, amount, fee);
    }

    public Transaction createTransfer(BankAccount from, BankAccount to, double amount, double fee) {
        return create(TransactionType.TRANSFER, from.getAccountNumber(), to.getAccountNumber(), amount, fee);
    }

    private Transaction create(TransactionType type, String from, String to, double amount, double fee) {
        return new Transaction(
                UUID.randomUUID().toString(),
                type,
                from,
                to,
                amount,
                fee,
                LocalDateTime.now()
        );
    }

    private String getIdentifier(Withdraw object) {
        if (object instanceof BankAccount) {
            return ((BankAccount) object).getAccountNumber();
        }
        return "CREDIT_CARD";
    }
}

