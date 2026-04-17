import br.com.zenon.fraud.*;

void main() throws IOException {
    TransactionIngestor ingestor = new TransactionIngestor();
    List<Transaction> transactions = ingestor.readTransactionsFromFile(0, 0, "data/PS_20174392719_1491204439457_log.csv");

    TransactionRepository repo = new TransactionListRepository(transactions);
    //TransactionRepository repo = new TransactionMapRepository(transactions);

    long ini,fim;
    ini = System.currentTimeMillis();

    repo.findTransactionByName("c123456").ifPresent(System.out::println);
    repo.findTransactionByName("C1671590089").ifPresent(System.out::println);
    fim = System.currentTimeMillis();
    System.out.println("Tempo de busca: " + (System.currentTimeMillis() - ini) + "ms");

    System.out.println(transactions.size());
}