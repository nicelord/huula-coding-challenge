import domain.amount.Amount;
import domain.amount.AmountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AmountTest {

    @DisplayName("should throw error if value is null")
    @Test
    void test1() {
        assertThrows(AmountException.class, () -> new Amount(null));
    }

    @DisplayName("should throw error if value is negative")
    @Test
    void test2() {
        assertThrows(AmountException.class, () -> new Amount(-20D));
    }
}