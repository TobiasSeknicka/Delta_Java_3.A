package transfers;

import transactions.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransferLoggerService {

    private List<Transaction> transactions = new ArrayList<>();

    public void log(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getAll() {
        return transactions;
    }

    public List<Transaction> getByAccount(String accountNumber) {
        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (accountNumber.equals(transaction.getFromAccount())
                    || accountNumber.equals(transaction.getToAccount())) {
                result.add(transaction);
            }
        }

        return result;
    }

    public void printAll() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}
