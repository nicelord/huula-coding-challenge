package service;

public class CSVScanException extends RuntimeException {
    private final String message;

    public CSVScanException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
