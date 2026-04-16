package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FraudAnalyzer {
    public List<Transaction> getFraudTransactions(List<Transaction> transactions){
        List<Transaction> fraudTransactions = transactions.stream().filter(Transaction::isFraud).toList();
        //fraudTransactions.forEach(System.out::println);
        System.out.println("Quantidade de fraudes encontradas: "+fraudTransactions.size());
        return fraudTransactions;
    }

    public void printTop3Frauds(List<Transaction> transactions){
        List<Transaction> top3TransactionFraud = transactions.stream().filter(Transaction::isFraud).sorted(Comparator.comparing(Transaction::amount).reversed()).limit(3).toList();
        System.out.println("2. Top 3 Fraudes de Maior Valor:");
        top3TransactionFraud.forEach(t -> System.out.println(t.amount().toPlainString()));
    }

    public void printSuspectFraudsCustomers(List<Transaction> transactions){
        Map<String, List<Transaction>> fraudByOriginCustomerName = transactions.stream().filter(Transaction::isFraud).collect(Collectors.groupingBy(t -> t.origin().getName()));
        System.out.println("3. Suspect Customers:");
        fraudByOriginCustomerName.keySet().forEach(System.out::println);
    }

    public void printFraudsAmount(List<Transaction> transactions){
        BigDecimal sumFraudAmount = transactions.stream().filter(Transaction::isFraud).map(Transaction::amount).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("4. Prejuizo Total: " + sumFraudAmount.toPlainString());
    }

    public void printFraudsByType(List<Transaction> transactions){
        Map<TransactionType, List<Transaction>> fraudByType= transactions.stream().filter(Transaction::isFraud).collect(Collectors.groupingBy(Transaction::type));

        System.out.println("5. Fraudes por Tipo:");
        fraudByType.forEach((k, v) -> System.out.println(" - "+k.name() + ": "+v.size()));
    }

}
