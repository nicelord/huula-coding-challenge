package service;

public class AnalyzerOutput {
    private final long numberOfTransactions;
    private final Double averageTransactionValue;

    public AnalyzerOutput(long numberOfTransactions, Double averageTransactionValue) {
        this.numberOfTransactions = numberOfTransactions;
        this.averageTransactionValue = averageTransactionValue;
    }


    public long getNumberOfTransactions() {
        return numberOfTransactions;
    }


    public Double getAverageTransactionValue() {
        return averageTransactionValue;
    }

}
