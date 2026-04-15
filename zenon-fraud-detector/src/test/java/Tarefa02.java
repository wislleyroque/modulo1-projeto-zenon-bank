import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionCustomer;
import br.com.zenon.fraud.TransactionType;

void main() {
    List<Transaction> transactions = new ArrayList<>();

    TransactionCustomer origin = new TransactionCustomer("C1231006815", new BigDecimal("170136.0"), new BigDecimal("160296.36"));
    TransactionCustomer recipient = new TransactionCustomer("M1979787155", new BigDecimal("0.0"), new BigDecimal("0.00"));
    Transaction transaction = new Transaction(1, TransactionType.PAYMENT, new BigDecimal("9839.64"), origin, recipient, false, false);
    transactions.add(transaction);

    TransactionCustomer origin1 = new TransactionCustomer("C1280323807", new BigDecimal("850002.52"), new BigDecimal("0.0"));
    TransactionCustomer recipient1 = new TransactionCustomer("C873221189", new BigDecimal("6510099.11"), new BigDecimal("7360101.63"));
    Transaction transaction1 = new Transaction(743, TransactionType.CASH_OUT, new BigDecimal("850002.52"), origin1, recipient1, true, false);
    transactions.add(transaction1);
    transactions.forEach(System.out::println);

}
