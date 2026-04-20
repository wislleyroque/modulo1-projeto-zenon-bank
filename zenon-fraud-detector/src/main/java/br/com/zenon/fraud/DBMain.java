package br.com.zenon.fraud;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class DBMain {
    static void main() throws SQLException, IOException {
        String name = "C1231006815";
        TransactionIngestor ing = new TransactionIngestor();
        List<Transaction> transactions = ing.readTransactionsFromFile(0, 10000, "data/PS_20174392719_1491204439457_log.csv");


        TransactionRepository db = new TransactionSQLRepository();
        Optional<Transaction> t1 = db.findByOriginName(name);

        if (t1.isPresent()) {
            System.out.println("Transaction found: " + t1.get());
        } else {
            TransactionRepository repo = new TransactionListRepository(transactions);
            Optional<Transaction> t2 = repo.findByOriginName(name);

            if (t2.isPresent()) {
                long ini, fim;
                ini = System.currentTimeMillis();

                for (Transaction transaction : transactions) {
                    db.save(transaction);
                }

                fim = System.currentTimeMillis();
                System.out.println("Saving transaction to database in " + (fim - ini) + " ms");
                t1 = db.findByOriginName(name);
                t1.ifPresent(transaction -> System.out.println("Transaction found in DB: " + transaction));
                t1 = db.findByOriginName("c12345");
                t1.ifPresent(transaction -> System.out.println("Transaction found in DB: " + transaction));
            }
        }


    }
}
