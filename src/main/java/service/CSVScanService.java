package service;

import domain.amount.Amount;
import domain.merchant.Merchant;
import domain.transaction.Transaction;
import domain.transaction.TransactionException;
import domain.transaction.TransactionType;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CSVScanService {

    private final String filePath;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    Function<String, Transaction> convertPayment = s -> {
        String[] coll = s.split(",");
        LocalDateTime transactionDate = LocalDateTime.parse(coll[1].trim(), dateTimeFormatter);
        Amount amount = new Amount(Double.valueOf(coll[2].trim()));
        Merchant merchant = new Merchant(coll[3].trim());
        return new Transaction(coll[0].trim(), merchant, amount, TransactionType.PAYMENT, null, transactionDate);
    };
    BiFunction<String, List<Transaction>, Transaction> convertReversal = (s, payments) -> {
        String[] coll = s.split(",");
        LocalDateTime transactionDate = LocalDateTime.parse(coll[1].trim(), dateTimeFormatter);
        Amount amount = new Amount(Double.valueOf(coll[2].trim()));
        Merchant merchant = new Merchant(coll[3].trim());
        String relatedTransactionId = coll[5].trim();
        return new Transaction(coll[0].trim(), merchant, amount, TransactionType.REVERSAL, payments.stream()
                .filter(transaction -> transaction.getId().equals(relatedTransactionId))
                .findFirst()
                .orElseThrow(() -> new TransactionException("referred transaction not found")),
                transactionDate);
    };

    public CSVScanService(String filePath) {
        this.filePath = filePath;
    }

    public List<Transaction> doScan() {
        List<Transaction> fakeDB = new ArrayList<>();

        File csvFile = new File(filePath);
        List<Transaction> payments = parsePayment(csvFile);
        List<Transaction> reversals = parseReversal(csvFile, payments);
        fakeDB.addAll(payments);
        fakeDB.addAll(reversals);
        fakeDB.sort(Comparator.comparing(Transaction::getDateTime));

        return fakeDB;
    }

    List<Transaction> parsePayment(File file) {
        try {
            InputStream inputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            return reader.lines().skip(1)
                    .filter(s -> !s.isEmpty() && s.split(",")[4].trim().equals("PAYMENT"))
                    .map(convertPayment)
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
        }

        throw new CSVScanException("unable to parse csv");
    }

    List<Transaction> parseReversal(File file, List<Transaction> payments) {
        try {
            InputStream inputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            return reader.lines().skip(1)
                    .filter(s -> !s.isEmpty() && s.split(",")[4].trim().equals("REVERSAL"))
                    .map(s -> convertReversal.apply(s, payments))
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
        }

        throw new CSVScanException("unable to parse csv");
    }
}
