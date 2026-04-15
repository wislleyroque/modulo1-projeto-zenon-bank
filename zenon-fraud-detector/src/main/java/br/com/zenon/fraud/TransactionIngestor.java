package br.com.zenon.fraud;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionIngestor {

    public List<Transaction> readTransactionsFromFile(long startLine, long numberOfLines) throws IOException {
        Path path = Paths.get("data/PS_20174392719_1491204439457_log.csv");

        try (var lines = Files.lines(path)) {

            long ini, fim;

            ini = System.nanoTime();
            List<String> result = lines.skip(startLine + 1).limit(numberOfLines).toList();

            List<Transaction> transactions = new ArrayList<>();
            result.stream()
                    .map(line -> line.split(","))
                    .map(parts -> new Transaction(
                            Integer.parseInt(parts[0]
                            ), TransactionType.valueOf(parts[1]),
                            new BigDecimal(parts[2]),
                            new TransactionCustomer(parts[3], new BigDecimal(parts[4]), new BigDecimal(parts[5])),
                            new TransactionCustomer(parts[6], new BigDecimal(parts[7]), new BigDecimal(parts[8])),
                            (!parts[9].equals("0")),
                            (!parts[9].equals("0"))
                    )).forEach(transactions::add);
            fim = System.nanoTime();
            System.out.println("Tempo de processamento de: " + result.size() + " linhas foi de: " + ((fim-ini)/1000000) + "ms.");
            return transactions;
        }
    }
}
