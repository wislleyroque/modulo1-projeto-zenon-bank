package br.com.zenon.fraud;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ReportMain
{
    public static void main(String[] args) throws IOException {

        TransactionReport transactionReport = new TransactionReport();
        TransactionReport.ReportStatics rs =  transactionReport.generateTransactionReport();

        ResourceBundle bundlePT = ResourceBundle.getBundle("report", new Locale("pt", "BR"));
        System.out.println(MessageFormat.format(bundlePT.getString("report.summary"), rs.totalLines(), rs.totalFrauds(), rs.totalAmount()));

        System.out.println("---------------------------------");
        ResourceBundle bundleEN = ResourceBundle.getBundle("report", Locale.getDefault());
        System.out.println(MessageFormat.format(bundleEN.getString("report.summary"), rs.totalLines(), rs.totalFrauds(), rs.totalAmount()));
    }
}
