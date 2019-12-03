package domain.transaction;

import domain.amount.Amount;
import domain.merchant.Merchant;

import java.time.LocalDateTime;

public class Transaction {
    private final String id;
    private final Merchant merchant;
    private final Amount amount;
    private final TransactionType type;
    private final Transaction relatedTransaction;
    private final LocalDateTime dateTime;

    public Transaction(String id, Merchant merchant, Amount amount, TransactionType type, Transaction relatedTransaction, LocalDateTime dateTime) {

        validate(id, merchant, amount, type, relatedTransaction, dateTime);

        this.id = id;
        this.merchant = merchant;
        this.amount = amount;
        this.type = type;
        this.relatedTransaction = relatedTransaction;
        this.dateTime = dateTime;
    }

    void validate(String id, Merchant merchant, Amount amount, TransactionType type, Transaction relatedTransaction, LocalDateTime dateTime) {

        if (id == null) {
            throw new TransactionException("id of transaction cannot be null");
        }
        if (merchant == null) {
            throw new TransactionException("merchant cannot be null");
        }
        if (amount == null) {
            throw new TransactionException("amount of transaction cannot be null");
        }
        if (type == null) {
            throw new TransactionException("type of transaction cannot be null");
        }
        if (dateTime == null) {
            throw new TransactionException("date of transaction cannot be null");
        }

        if (type.equals(TransactionType.REVERSAL)) {
            if (relatedTransaction == null) {
                throw new TransactionException("reversal transaction must have related transaction");
            }
            if (relatedTransaction.getDateTime().isAfter(dateTime)) {
                throw new TransactionException("related transaction must in the past time");
            }
            if (amount.getValue() > relatedTransaction.getAmount().getValue()) {
                throw new TransactionException("reversal transaction amount cannot more than related payment amount");
            }

            if (!relatedTransaction.getMerchant().getName().equals(merchant.getName())) {
                throw new TransactionException("related transaction has different merchant");
            }

            if (!relatedTransaction.getType().equals(TransactionType.PAYMENT)) {
                throw new TransactionException("related transaction should PAYMENT type");
            }

        }

    }

    public String getId() {
        return id;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public Amount getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public Transaction getRelatedTransaction() {
        return relatedTransaction;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}


