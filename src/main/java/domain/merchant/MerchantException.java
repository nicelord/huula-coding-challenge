package domain.merchant;

public class MerchantException extends RuntimeException {
    private final String message;

    public MerchantException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
