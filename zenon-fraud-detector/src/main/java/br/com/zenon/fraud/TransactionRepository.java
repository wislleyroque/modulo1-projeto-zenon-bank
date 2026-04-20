package br.com.zenon.fraud;

import java.util.Optional;

public interface TransactionRepository {
    Optional<Transaction> findByOriginName(String name);
    void save(Transaction transaction);
}
