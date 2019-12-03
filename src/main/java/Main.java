import domain.transaction.Transaction;
import service.AnalyzerOutput;
import service.CSVScanService;
import service.TransactionAnalyzerService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        if (args.length < 3) {
            System.err.println("Invalid parameter");
            System.err.println("Usage : java -jar <jarfile.jar> \"<input-file>\" \"<merchant-name>\" \"<from-date>\" \"<to-date>\"");
            System.err.println("Note !! -- accepted date format is \"dd/MM/yyyy HH:mm:ss\"");
            System.err.println(String.format("Example : java -jar main.jar \"%s\" \"%s\" \"%s\" \"%s\"", "transactions.csv", "Kwik-E-Mart", "20/08/2018 12:00:00", "20/08/2018 13:00:00"));
            return;
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        String inputFile = args[0];
        String merchantName = args[1];
        LocalDateTime from = LocalDateTime.parse(args[2], dateTimeFormatter);
        LocalDateTime to = LocalDateTime.parse(args[3], dateTimeFormatter);

        CSVScanService csvScanService = new CSVScanService(inputFile);
        List<Transaction> transactions = csvScanService.doScan();

        TransactionAnalyzerService transactionAnalyzerService = new TransactionAnalyzerService(transactions);

        AnalyzerOutput analyzerOutput = transactionAnalyzerService.analyzeByMerchantAtTheGivenTimeRange(merchantName, from, to);

        System.out.println(String.format("Number of transactions : %d", analyzerOutput.getNumberOfTransactions()));
        System.out.println(String.format("Average transaction value : %.2f", analyzerOutput.getAverageTransactionValue()));


    }

}
