package domain.transaction;

public class TransactionException extends RuntimeException {
    private final String message;

    public TransactionException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
