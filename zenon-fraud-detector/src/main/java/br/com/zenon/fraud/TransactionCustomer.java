package br.com.zenon.fraud;

import java.math.BigDecimal;

public class TransactionCustomer {
    private String name;
    private BigDecimal oldBalance;
    private BigDecimal newBalance;

    public TransactionCustomer(String name, BigDecimal oldBalance, BigDecimal newBalance) {
        this.name = name;
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(BigDecimal oldBalance) {
        this.oldBalance = oldBalance;
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }

    @Override
    public String toString() {
        return "TransactionCustomer{" +
                "name='" + name + '\'' +
                ", oldBalance=" + oldBalance +
                ", newBalance=" + newBalance +
                '}';
    }
}
