package domain.amount;

public class Amount {
    private final Double value;

    public Amount(Double value) {
        validate(value);
        this.value = value;
    }

    void validate(Double value) {
        if (value == null) {
            throw new AmountException("amount value cannot be null");
        }
        if (value < 0) {
            throw new AmountException("amount value cannot be negative");
        }
    }

    public Double getValue() {
        return value;
    }
}
