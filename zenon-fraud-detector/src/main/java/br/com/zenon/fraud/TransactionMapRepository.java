package br.com.zenon.fraud;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionMapRepository implements TransactionRepository {

    private final Map<String, List<Transaction>> transactionMap;

    public TransactionMapRepository(List<Transaction> transactions){
        this.transactionMap = transactions.stream().collect(Collectors.groupingBy(t -> t.origin().getName()));
    }

    @Override
    public Optional<Transaction> findTransactionByName(String name) {
        try {
            return transactionMap.get(name).stream().findFirst();
        } catch (Exception e) {
            System.err.println("Transação não encontrada para o cliente " + name);
            return Optional.empty();
        }
    }
}
