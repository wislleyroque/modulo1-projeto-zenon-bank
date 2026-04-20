package br.com.zenon.fraud;

import java.io.IOException;

public class ReportMain
{
    public static void main(String[] args) throws IOException {
        TransactionReport transactionReport = new TransactionReport();
        TransactionReport.ReportStatics rs =  transactionReport.generateTransactionReport();

        System.out.println("Total de linhas: " + rs.totalLines());
        System.out.println("Total de fraudes: " + rs.totalFrauds());
        System.out.println("Valor total transacionado " + rs.totalAmount().toPlainString());

    }
}
