import domain.amount.Amount;
import domain.merchant.Merchant;
import domain.transaction.Transaction;
import domain.transaction.TransactionType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.AnalyzerOutput;
import service.TransactionAnalyzerException;
import service.TransactionAnalyzerService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionAnalyzerServiceTest {

    static List<Transaction> goodList = new ArrayList<>();

    @BeforeAll
    static void setup() {
        Transaction t1 = new Transaction(UUID.randomUUID().toString(), new Merchant("domain.merchant.Merchant A"), new Amount(100D), TransactionType.PAYMENT, null, LocalDateTime.now().minusDays(10));
        Transaction t2 = new Transaction(UUID.randomUUID().toString(), new Merchant("domain.merchant.Merchant A"), new Amount(100D), TransactionType.PAYMENT, null, LocalDateTime.now().minusDays(9));
        Transaction t3 = new Transaction(UUID.randomUUID().toString(), new Merchant("domain.merchant.Merchant A"), new Amount(100D), TransactionType.PAYMENT, null, LocalDateTime.now().minusDays(8));
        Transaction t4 = new Transaction(UUID.randomUUID().toString(), new Merchant("domain.merchant.Merchant A"), new Amount(100D), TransactionType.PAYMENT, null, LocalDateTime.now().minusDays(7));
        Transaction t5 = new Transaction(UUID.randomUUID().toString(), new Merchant("domain.merchant.Merchant A"), new Amount(100D), TransactionType.PAYMENT, null, LocalDateTime.now().minusDays(6));
        Transaction t6 = new Transaction(UUID.randomUUID().toString(), new Merchant("domain.merchant.Merchant A"), new Amount(100D), TransactionType.REVERSAL, t1, LocalDateTime.now().minusDays(5));
        Transaction t7 = new Transaction(UUID.randomUUID().toString(), new Merchant("domain.merchant.Merchant B"), new Amount(100D), TransactionType.PAYMENT, null, LocalDateTime.now().minusDays(4));
        Transaction t8 = new Transaction(UUID.randomUUID().toString(), new Merchant("domain.merchant.Merchant B"), new Amount(100D), TransactionType.PAYMENT, null, LocalDateTime.now().minusDays(3));
        Transaction t9 = new Transaction(UUID.randomUUID().toString(), new Merchant("domain.merchant.Merchant B"), new Amount(100D), TransactionType.REVERSAL, t8, LocalDateTime.now().minusDays(2));
        Transaction t10 = new Transaction(UUID.randomUUID().toString(), new Merchant("domain.merchant.Merchant C"), new Amount(100D), TransactionType.PAYMENT, null, LocalDateTime.now().minusDays(1));

        goodList.add(t1);
        goodList.add(t2);
        goodList.add(t3);
        goodList.add(t4);
        goodList.add(t5);
        goodList.add(t6);
        goodList.add(t7);
        goodList.add(t8);
        goodList.add(t9);
        goodList.add(t10);
    }


    @DisplayName("should throw error if no transaction list provided")
    @Test
    void test1() {
        assertThrows(TransactionAnalyzerException.class, () -> new TransactionAnalyzerService(null));
    }

    @DisplayName("should throw error if time range invalid")
    @Test
    void test2() {
        TransactionAnalyzerService transactionAnalyzerService = new TransactionAnalyzerService(goodList);
        TransactionAnalyzerException transactionAnalyzerException = assertThrows(TransactionAnalyzerException.class, () -> transactionAnalyzerService.analyzeByMerchantAtTheGivenTimeRange("MERCHANT A", LocalDateTime.now(), LocalDateTime.now().minusDays(2)));
        assertEquals("from should before to", transactionAnalyzerException.getMessage());
    }

    @DisplayName("should throw error if name is null")
    @Test
    void test3() {
        TransactionAnalyzerService transactionAnalyzerService = new TransactionAnalyzerService(goodList);
        TransactionAnalyzerException transactionAnalyzerException = assertThrows(TransactionAnalyzerException.class, () -> transactionAnalyzerService.analyzeByMerchantAtTheGivenTimeRange(null, LocalDateTime.now(), LocalDateTime.now().plusDays(2)));
        assertEquals("merchant name cannot be null", transactionAnalyzerException.getMessage());
    }

    @DisplayName("should throw error if from date is null")
    @Test
    void test4() {
        TransactionAnalyzerService transactionAnalyzerService = new TransactionAnalyzerService(goodList);
        TransactionAnalyzerException transactionAnalyzerException = assertThrows(TransactionAnalyzerException.class, () -> transactionAnalyzerService.analyzeByMerchantAtTheGivenTimeRange("MERCHANT A", null, LocalDateTime.now().plusDays(2)));
        assertEquals("from date cannot be null", transactionAnalyzerException.getMessage());
    }

    @DisplayName("should throw error if to date is null")
    @Test
    void test5() {
        TransactionAnalyzerService transactionAnalyzerService = new TransactionAnalyzerService(goodList);
        TransactionAnalyzerException transactionAnalyzerException = assertThrows(TransactionAnalyzerException.class, () -> transactionAnalyzerService.analyzeByMerchantAtTheGivenTimeRange("MERCHANT A", LocalDateTime.now(), null));
        assertEquals("to date cannot be null", transactionAnalyzerException.getMessage());
    }


    @DisplayName("expect 4 of transactions and 100 average from domain.merchant.Merchant A")
    @Test
    void test6() {
        TransactionAnalyzerService transactionAnalyzerService = new TransactionAnalyzerService(goodList);
        AnalyzerOutput analyzerOutput = transactionAnalyzerService.analyzeByMerchantAtTheGivenTimeRange("domain.merchant.Merchant A", LocalDateTime.now().minusDays(11), LocalDateTime.now().minusDays(1));
        assertEquals(4, analyzerOutput.getNumberOfTransactions());
        assertEquals(100, analyzerOutput.getAverageTransactionValue());
    }

    @DisplayName("expect 1 transaction and 100 average from domain.merchant.Merchant B")
    @Test
    void test7() {
        TransactionAnalyzerService transactionAnalyzerService = new TransactionAnalyzerService(goodList);
        AnalyzerOutput analyzerOutput = transactionAnalyzerService.analyzeByMerchantAtTheGivenTimeRange("domain.merchant.Merchant B", LocalDateTime.now().minusDays(11), LocalDateTime.now().minusDays(1));
        assertEquals(1, analyzerOutput.getNumberOfTransactions());
        assertEquals(100, analyzerOutput.getAverageTransactionValue());
    }

    @DisplayName("expect 3 of transactions and 100 average from domain.merchant.Merchant A with the given time range")
    @Test
    void test8() {
        TransactionAnalyzerService transactionAnalyzerService = new TransactionAnalyzerService(goodList);
        AnalyzerOutput analyzerOutput = transactionAnalyzerService.analyzeByMerchantAtTheGivenTimeRange("domain.merchant.Merchant A", LocalDateTime.now().minusDays(11), LocalDateTime.now().minusDays(7));
        assertEquals(3, analyzerOutput.getNumberOfTransactions());
        assertEquals(100, analyzerOutput.getAverageTransactionValue());
    }


}