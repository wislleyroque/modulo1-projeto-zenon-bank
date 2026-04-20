import br.com.zenon.fraud.*;

void main() throws IOException {
    TransactionIngestor ingestor = new TransactionIngestor();
    List<Transaction> transactions = ingestor.readTransactionsFromFile(0, 100000, "data/PS_20174392719_1491204439457_log.csv");

    TransactionRepository repo = new TransactionListRepository(transactions);
    //TransactionRepository repo = new TransactionMapRepository(transactions);

    long ini,fim;
    ini = System.currentTimeMillis();

    repo.findByOriginName("c123456").ifPresent(System.out::println);
    repo.findByOriginName("C190861775").ifPresent(System.out::println);
    fim = System.currentTimeMillis();
    System.out.println("Tempo de busca: " + (System.currentTimeMillis() - ini) + "ms");
}