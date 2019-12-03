import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.CSVScanException;
import service.CSVScanService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CSVScanServiceTest {


    @DisplayName("should throw error if file not exists")
    @Test
    void test1() {
        String input = "src/test/resources/notexists.csv";
        CSVScanService csvScanService = new CSVScanService(input);
        assertThrows(CSVScanException.class, () -> csvScanService.doScan());
    }

    @DisplayName("expect 6 transactions from the given file")
    @Test
    void test2() {
        String input = "src/test/resources/input-test.csv";
        CSVScanService csvScanService = new CSVScanService(input);
        assertEquals(6, csvScanService.doScan().size());
    }

    @DisplayName("expect 5 transactions from the given file")
    @Test
    void test3() {
        String input = "src/test/resources/input-test2.csv";
        CSVScanService csvScanService = new CSVScanService(input);
        assertEquals(5, csvScanService.doScan().size());
    }

    @DisplayName("expect 5 and should skip unknownn transaction type")
    @Test
    void test4() {
        String input = "src/test/resources/input-test4.csv";
        CSVScanService csvScanService = new CSVScanService(input);
        assertEquals(5, csvScanService.doScan().size());
    }

    @DisplayName("should throw error if amount invalid")
    @Test
    void test5() {
        String input = "src/test/resources/input-test5.csv";
        CSVScanService csvScanService = new CSVScanService(input);
        assertThrows(NumberFormatException.class, () -> csvScanService.doScan());
    }
}