package domain.amount;

public class AmountException extends RuntimeException {
    private final String message;

    public AmountException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
