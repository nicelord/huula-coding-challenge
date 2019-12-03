import domain.merchant.Merchant;
import domain.merchant.MerchantException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MerchantTest {

    @DisplayName("should throw error if name is null")
    @Test
    void test1() {
        assertThrows(MerchantException.class, () -> new Merchant(null));
    }

}