package br.com.zenon.fraud;

import java.sql.*;
import java.util.Optional;

public class TransactionSQLRepository implements TransactionRepository {

    private final String urlConnection = "jdbc:mysql://localhost:3306/zenon_fraud";
    private final String user = "root";
    private final String password = "senha123";

    @Override
    public Optional<Transaction> findByOriginName(String name) {
        String query = """
                SELECT id,step,type,amount,nameOrig,oldbalanceOrg,newbalanceOrig,nameDest,oldbalanceDest,newbalanceDest,isFraud,isFlaggedFraud FROM zenon_fraud.TRANSACTIONS WHERE nameOrig = ?""";
        try (Connection connection = DriverManager.getConnection(urlConnection, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {

                    Transaction t = new Transaction(
                            rs.getInt("step"),
                            TransactionType.valueOf(rs.getString("type")),
                            rs.getBigDecimal("amount"),
                            new TransactionCustomer(rs.getString("nameOrig"), rs.getBigDecimal("oldbalanceOrg"), rs.getBigDecimal("newbalanceOrig")),
                            new TransactionCustomer(rs.getString("nameDest"), rs.getBigDecimal("oldbalanceDest"), rs.getBigDecimal("newbalanceDest")),
                            rs.getInt("isFraud") != 0,
                            rs.getInt("isFlaggedFraud") != 0
                    );

                    return Optional.of(t);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public void save(Transaction transaction) {

        // We list 11 columns and provide 11 placeholders
        String query = """
                INSERT INTO TRANSACTIONS
                (step, type, amount, nameOrig, oldbalanceOrg, newbalanceOrig, nameDest, oldbalanceDest, newbalanceDest, isFraud, isFlaggedFraud) 
                VALUES 
                (?,?,?,?,?,?,?,?,?,?,?)
                """;

        try (Connection connection = DriverManager.getConnection(urlConnection, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Indexing starts at 1
            preparedStatement.setInt(1, transaction.step());
            preparedStatement.setString(2, transaction.type().name()); // Must match ENUM values exactly
            preparedStatement.setBigDecimal(3, transaction.amount());
            preparedStatement.setString(4, transaction.origin().getName());
            preparedStatement.setBigDecimal(5, transaction.origin().getOldBalance());
            preparedStatement.setBigDecimal(6, transaction.origin().getNewBalance());
            preparedStatement.setString(7, transaction.recipient().getName());
            preparedStatement.setBigDecimal(8, transaction.recipient().getOldBalance());
            preparedStatement.setBigDecimal(9, transaction.recipient().getNewBalance());
            preparedStatement.setInt(10, transaction.isFraud() ? 1 : 0);
            preparedStatement.setInt(11, transaction.isFlaggedFraud() ? 1 : 0);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Não foi possível inserir os dados da transação");
            } else {
                System.out.println("Dados inseridos: " + rowsAffected);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}



