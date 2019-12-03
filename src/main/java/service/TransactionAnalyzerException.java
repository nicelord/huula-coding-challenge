package service;

public class TransactionAnalyzerException extends RuntimeException {
    private final String message;

    public TransactionAnalyzerException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
