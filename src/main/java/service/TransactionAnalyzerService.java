package service;

import domain.transaction.Transaction;
import domain.transaction.TransactionType;

import java.time.LocalDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionAnalyzerService {

    private final List<Transaction> transactionList;

    public TransactionAnalyzerService(List<Transaction> transactionList) {
        if (transactionList == null) {
            throw new TransactionAnalyzerException("transaction list cannot be null");
        }
        this.transactionList = transactionList;
    }

    public AnalyzerOutput analyzeByMerchantAtTheGivenTimeRange(String merchantName, LocalDateTime from, LocalDateTime to) {
        validate(merchantName, from, to);

        List<Transaction> reversedTransaction = transactionList.stream()
                .filter(transaction -> transaction.getMerchant().getName().equals(merchantName))
                .filter(transaction -> transaction.getType().equals(TransactionType.REVERSAL))
                .map(Transaction::getRelatedTransaction)
                .collect(Collectors.toList());

        List<Transaction> paymentTransactions = transactionList.stream()
                .filter(transaction -> transaction.getMerchant().getName().equals(merchantName))
                .filter(transaction -> transaction.getType().equals(TransactionType.PAYMENT))
                .filter(isBetween(from, to)::apply)
                .filter(transaction -> !reversedTransaction.stream()
                        .anyMatch(transaction1 -> transaction.getId().equals(transaction1.getId())))
                .collect(Collectors.toList());

        DoubleSummaryStatistics collect = paymentTransactions.stream()
                .collect(Collectors.summarizingDouble(value -> value.getAmount().getValue()));

        return new AnalyzerOutput(collect.getCount(), collect.getAverage());
    }


    void validate(String merchantName, LocalDateTime from, LocalDateTime to) {
        if (merchantName == null) {
            throw new TransactionAnalyzerException("merchant name cannot be null");
        }
        if (from == null) {
            throw new TransactionAnalyzerException("from date cannot be null");
        }
        if (to == null) {
            throw new TransactionAnalyzerException("to date cannot be null");
        }
        if (from.isAfter(to)) {
            throw new TransactionAnalyzerException("from should before to");
        }
    }

    Function<Transaction, Boolean> isBetween(LocalDateTime from, LocalDateTime to) {
        return transaction -> transaction.getDateTime().isBefore(to) && transaction.getDateTime().isAfter(from);
    }
}
