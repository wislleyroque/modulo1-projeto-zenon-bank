import br.com.zenon.fraud.FraudAnalyzer;
import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionIngestor;

void main() throws IOException {
    TransactionIngestor ingestor = new TransactionIngestor();
    List<Transaction> transactions = ingestor.readTransactionsFromFile(0, 50000, "data/PS_20174392719_1491204439457_log.csv");

    FraudAnalyzer  fraudAnalyzer = new FraudAnalyzer();
    List<Transaction> fraudTransactions = fraudAnalyzer.getFraudTransactions(transactions);

    fraudAnalyzer.printTop3Frauds(transactions);
    fraudAnalyzer.printSuspectFraudsCustomers(transactions);
    fraudAnalyzer.printFraudsAmount(transactions);
    fraudAnalyzer.printFraudsByType(transactions);

}