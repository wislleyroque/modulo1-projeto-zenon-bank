package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Objects;

public class TransactionCustomer {
    private String name;
    private BigDecimal oldBalance;
    private BigDecimal newBalance;

    public TransactionCustomer(String name, BigDecimal oldBalance, BigDecimal newBalance) {
        if (name.isEmpty()) throw new IllegalArgumentException("name should not be empty");
        if (oldBalance.signum() < 0) throw new IllegalArgumentException("oldBalance should be positive: " + oldBalance);
        if (newBalance.signum() < 0) throw new IllegalArgumentException("newBalance should be positive: " + newBalance);
        this.name = name;
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isEmpty()) throw new IllegalArgumentException("name should not be empty");
        this.name = name;
    }

    public BigDecimal getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(BigDecimal oldBalance) {
        if (oldBalance.signum() < 0) throw new IllegalArgumentException("oldBalance should be positive: " + oldBalance);
        this.oldBalance = oldBalance;
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
        if (newBalance.signum() < 0) throw new IllegalArgumentException("newBalance should be positive: " + newBalance);
        this.newBalance = newBalance;
    }

    @Override
    public String toString() {
        return "TransactionCustomer{" +
                "name='" + name + '\'' +
                ", oldBalance=" + oldBalance.toPlainString() +
                ", newBalance=" + newBalance.toPlainString() +
                '}';
    }

}
