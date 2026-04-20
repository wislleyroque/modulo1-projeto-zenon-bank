package br.com.zenon.fraud;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TransactionListRepository implements TransactionRepository {

    private final List<Transaction> transactions;

    public TransactionListRepository(List<Transaction> transactions) {
        this.transactions = transactions;
    }



    @Override
    public Optional<Transaction> findByOriginName(String name){
        try {
            return Optional.of(transactions.stream().filter(transaction -> transaction.origin().getName().equals(name)).findFirst().orElseThrow(() -> new RuntimeException("Transação não encontrada para o cliente " + name)));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void save(Transaction transaction) {
        transactions.add(transaction);
    }
}
