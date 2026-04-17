package br.com.zenon.fraud;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


public class TransactionReport {

    public void generateTransactionReport() throws IOException {
        TransactionIngestor transactionIngestor = new TransactionIngestor();

        long totalLines = 0;
        long totalFrauds = 0;
        BigDecimal totalAmount = BigDecimal.ZERO;

        int offset = 1; // começa depois do header

        while (true) {
            String file = "data/PS_20174392719_1491204439457_log.csv";
            long BATCH_SIZE = 200000;
            List<Transaction> tmpTransactions =
                    transactionIngestor.readTransactionsFromFile(offset, BATCH_SIZE, file);

            if (tmpTransactions.isEmpty()) break;

            totalFrauds = totalFrauds + tmpTransactions.stream().filter(Transaction::isFraud).count();
            totalLines = totalLines + tmpTransactions.size();
            BigDecimal batchSum = tmpTransactions.stream()
                    .map(Transaction::amount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            totalAmount = totalAmount.add(batchSum);

            offset += tmpTransactions.size(); // avança exatamente o que leu
        }

        System.out.println("Total de linhas: " + totalLines);
        System.out.println("Total de fraudes: " + totalFrauds);
        System.out.println("Valor total transacionado " + totalAmount.toPlainString());

    }

}
