package transactions;

import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private TransactionType type;
    private String fromAccount;
    private String toAccount;
    private double amount;
    private double fee;
    private LocalDateTime createdAt;

    public Transaction(String id, TransactionType type, String fromAccount, String toAccount, double amount, double fee, LocalDateTime createdAt) {
        this.id = id;
        this.type = type;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.fee = fee;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public double getAmount() {
        return amount;
    }

    public double getFee() {
        return fee;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return createdAt + " | " + type + " | " + fromAccount + " -> " + toAccount
                + " | amount: " + amount + " | fee: " + fee;
    }
}
