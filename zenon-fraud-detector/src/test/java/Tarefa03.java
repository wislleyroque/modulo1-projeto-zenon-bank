import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionIngestor;

void main() throws IOException {
    TransactionIngestor transactionIngestor = new TransactionIngestor();
    List<Transaction> transactions = transactionIngestor.readTransactionsFromFile(0L,1000, "data/PS_20174392719_1491204439457_log.csv");
    transactions.stream().limit(10).forEach(System.out::println);
}