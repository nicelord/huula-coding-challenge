import domain.amount.Amount;
import domain.merchant.Merchant;
import domain.transaction.Transaction;
import domain.transaction.TransactionException;
import domain.transaction.TransactionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionTest {
    @DisplayName("should throw error if id null")
    @Test
    void test1() {
        Amount amount = new Amount(200D);
        Merchant merchant = new Merchant("MERCHANT A");
        assertThrows(TransactionException.class, () -> new Transaction(null, merchant, amount, TransactionType.PAYMENT, null, LocalDateTime.now()));
    }

    @DisplayName("should throw error if merchant null")
    @Test
    void test2() {
        Amount amount = new Amount(200D);
        assertThrows(TransactionException.class, () -> new Transaction(UUID.randomUUID().toString(), null, amount, TransactionType.PAYMENT, null, LocalDateTime.now()));
    }

    @DisplayName("should throw error if amount is null")
    @Test
    void test3() {
        Merchant merchant = new Merchant("MERCHANT A");
        assertThrows(TransactionException.class, () -> new Transaction(UUID.randomUUID().toString(), merchant, null, TransactionType.PAYMENT, null, LocalDateTime.now()));
    }

    @DisplayName("should throw error if type is null")
    @Test
    void test4() {
        Merchant merchant = new Merchant("MERCHANT A");
        Amount amount = new Amount(200D);
        assertThrows(TransactionException.class, () -> new Transaction(UUID.randomUUID().toString(), merchant, amount, null, null, LocalDateTime.now()));
    }

    @DisplayName("should throw error if date is null")
    @Test
    void test5() {
        Merchant merchant = new Merchant("MERCHANT A");
        Amount amount = new Amount(200D);
        assertThrows(TransactionException.class, () -> new Transaction(UUID.randomUUID().toString(), merchant, amount, TransactionType.PAYMENT, null, null));
    }

    @DisplayName("should throw error if type is reversal but no related transaction provided")
    @Test
    void test6() {
        Merchant merchant = new Merchant("MERCHANT A");
        Amount amount = new Amount(200D);
        assertThrows(TransactionException.class, () -> new Transaction(UUID.randomUUID().toString(), merchant, amount, TransactionType.REVERSAL, null, LocalDateTime.now()));
    }

    @DisplayName("should throw error if type is reversal but related merchant not match")
    @Test
    void test7() {
        Merchant merchant = new Merchant("MERCHANT A");
        Amount amount = new Amount(200D);

        Transaction relatedTransaction = new Transaction(UUID.randomUUID().toString(), new Merchant("MERCHANT B"), amount, TransactionType.PAYMENT, null, LocalDateTime.now().minusDays(1));
        assertThrows(TransactionException.class, () -> new Transaction(UUID.randomUUID().toString(), merchant, amount, TransactionType.REVERSAL, relatedTransaction, LocalDateTime.now()));
    }

//    @DisplayName("should throw error if related transaction type is not PAYMENT")
//    @Test
//    void test8() {
//        domain.merchant.Merchant merchant = new domain.merchant.Merchant("MERCHANT A");
//        domain.amount.Amount amount = new domain.amount.Amount(200D);
//
//        domain.transaction.Transaction relatedTransaction = new domain.transaction.Transaction(UUID.randomUUID().toString(), new domain.merchant.Merchant("MERCHANT A"), amount, domain.transaction.TransactionType.REVERSAL, null, LocalDateTime.now().minusDays(1));
//        assertThrows(domain.transaction.TransactionException.class, () -> new domain.transaction.Transaction(UUID.randomUUID().toString(), merchant, amount, domain.transaction.TransactionType.REVERSAL, relatedTransaction, LocalDateTime.now()));
//    }
}