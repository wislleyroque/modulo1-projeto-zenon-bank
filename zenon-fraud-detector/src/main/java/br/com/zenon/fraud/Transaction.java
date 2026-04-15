package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(int step, TransactionType type, BigDecimal amount, TransactionCustomer origin,
                          TransactionCustomer recipient, boolean isFraud,
                          boolean isFlaggedFraud) {

    public Transaction {
        if ( step <= 0) throw new IllegalArgumentException("step should be positive: " + step);
        if ( amount.signum() < 0) throw new IllegalArgumentException("amount should be positive: " + amount);

    }

    @Override
    public String toString() {
        return "Transaction{" +
                "step=" + step +
                ", type=" + type +
                ", amount=" + amount +
                ", origin=" + origin +
                ", recipient=" + recipient +
                ", isFraud=" + isFraud +
                ", isFlaggedFraud=" + isFlaggedFraud +
                '}';
    }
}
