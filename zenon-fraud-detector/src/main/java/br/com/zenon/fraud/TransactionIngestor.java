package br.com.zenon.fraud;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class TransactionIngestor {

    public List<Transaction> readTransactionsFromFile(long startLine, long numberOfLines, String fileName) throws IOException {
        Path path = Paths.get(fileName);

        try (var lines = Files.lines(path)) {

            long ini, fim;

            ini = System.nanoTime();

            List<Optional<Transaction>> transactions;
            if (numberOfLines > 0) {
                transactions = lines.skip(startLine).limit(numberOfLines).map(this::parseTransaction).toList();
            } else {
                transactions = lines.skip(1).map(this::parseTransaction).toList();
            }


            fim = System.nanoTime();
            System.out.println("Tempo de processamento de: " + transactions.size() + " linhas foi de: " + ((fim - ini) / 1000000) + "ms.");
            return transactions.stream().filter(Optional::isPresent).map(Optional::get).toList();
        }
    }

    private Optional<Transaction> parseTransaction(String line) {
        String[] parts = line.split(",");
        try {
            Transaction transaction = new Transaction(
                    Integer.parseInt(parts[0]
                    ), TransactionType.valueOf(parts[1]),
                    new BigDecimal(parts[2]),
                    new TransactionCustomer(parts[3], new BigDecimal(parts[4]), new BigDecimal(parts[5])),
                    new TransactionCustomer(parts[6], new BigDecimal(parts[7]), new BigDecimal(parts[8])),
                    (parts[9].equals("1")),
                    (parts[10].equals("1")));
            return Optional.of(transaction);
        } catch (Exception e) {
            System.err.println("Error: " + line + " | " + e);
        }
        return Optional.empty();
    }
}
