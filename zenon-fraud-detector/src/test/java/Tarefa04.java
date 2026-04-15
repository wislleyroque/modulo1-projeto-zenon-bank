import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionIngestor;

void main() throws IOException {
    TransactionIngestor transactionIngestor = new TransactionIngestor();
    List<Transaction> transactions = transactionIngestor.readTransactionsFromFile(0L,1000, "data/paysim_with_bad_data.csv");
    System.out.println(transactions.size());
    transactions.stream().forEach(System.out::println);
}