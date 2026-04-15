import br.com.zenon.fraud.TransactionIngestor;

void main() throws IOException {
    TransactionIngestor transactionIngestor = new TransactionIngestor();
    transactionIngestor.readTransactionsFromFile(0L,7000000L);

}