package domain.merchant;

public class Merchant {
    private final String name;

    public Merchant(String name) {
        if (name == null) {
            throw new MerchantException("merchant name cannot be null");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
