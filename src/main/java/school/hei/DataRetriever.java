package school.hei;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {
    private final DBConnection dbConnection = new DBConnection();

    public List<InvoiceTotal> findInvoicesTotals() {
        List<InvoiceTotal> result = new ArrayList<>();
        String query = """
                 SELECT i.id, i.customer_name, SUM(il.quantity * il.unit_price) AS total_invoice
                 FROM invoice i
                          INNER JOIN invoice_line il
                                     ON i.id = il.invoice_id
                 GROUP BY i.customer_name, i.id
                 ORDER BY i.id;
                \s""";
        try (Connection c = dbConnection.getConnection()) {
            PreparedStatement p = c.prepareStatement(query);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                InvoiceTotal i = new InvoiceTotal();
                i.setId(r.getInt(1));
                i.setCustomerName(r.getString(2));
                i.setAmount(r.getDouble(3));
                result.add(i);
            }
            return result;
        } catch (SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }

    public List<InvoiceTotal> findConfirmedAndPaidInvoicesTotals() {
        List<InvoiceTotal> result = new ArrayList<>();
        String query = """
                 SELECT i.id,
                        i.customer_name,
                        i.status,
                        SUM(il.quantity * il.unit_price) AS total_invoice
                 FROM invoice i
                          INNER JOIN invoice_line il
                                     ON i.id = il.invoice_id
                 WHERE i.status = 'PAID'
                    or i.status = 'CONFIRMED'
                 GROUP BY i.customer_name, i.id, i.status
                 ORDER BY i.id;
                \s""";
        try (Connection c = dbConnection.getConnection()) {
            PreparedStatement p = c.prepareStatement(query);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                InvoiceTotal i = new InvoiceTotal();
                i.setId(r.getInt(1));
                i.setCustomerName(r.getString(2));
                i.setStatus(Status.valueOf(r.getString(3)));
                i.setAmount(r.getDouble(4));
                result.add(i);
            }
            return result;
        } catch (SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }

    public Double getPaidInvoicesTotals() throws SQLException {
        String query = """
                SELECT SUM(il.quantity * il.unit_price) AS total_paid
                FROM invoice i
                         INNER JOIN invoice_line il
                                    ON i.id = il.invoice_id
                WHERE i.status = 'PAID';
                """;
        return getInvoicesStatusTotalsByQuery(query);
    }

    public Double getConfirmedInvoicesTotals() throws SQLException {
        String query = """
                SELECT SUM(il.quantity * il.unit_price) AS total_confirmed
                FROM invoice i
                         INNER JOIN invoice_line il
                                    ON i.id = il.invoice_id
                WHERE i.status = 'CONFIRMED';
                """;
        return getInvoicesStatusTotalsByQuery(query);
    }

    public Double getDraftInvoicesTotals() throws SQLException {
        String query = """
                SELECT SUM(il.quantity * il.unit_price) AS total_draft
                FROM invoice i
                         INNER JOIN invoice_line il
                                    ON i.id = il.invoice_id
                WHERE i.status = 'DRAFT';
                """;
        return getInvoicesStatusTotalsByQuery(query);
    }

    private Double getInvoicesStatusTotalsByQuery(String query) throws SQLException {
        try (Connection c = dbConnection.getConnection()) {
            PreparedStatement p = c.prepareStatement(query);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                return r.getDouble(1);
            }
        }
        return 0.0;
    }

    public InvoiceStatusTotals computeStatusTotals() throws SQLException {
        DataRetriever dataRetriever = new DataRetriever();
        InvoiceStatusTotals invoiceStatusTotals = new InvoiceStatusTotals();
        invoiceStatusTotals.setTotal_paid(dataRetriever.getPaidInvoicesTotals());
        invoiceStatusTotals.setTotal_confirmed(dataRetriever.getConfirmedInvoicesTotals());
        invoiceStatusTotals.setTotal_draft(dataRetriever.getDraftInvoicesTotals());
        return invoiceStatusTotals;
    }

    public Double computeWeightedTurnover() throws SQLException {
        DataRetriever dr = new DataRetriever();
        InvoiceStatusTotals t = dr.computeStatusTotals();
        return (t.getTotal_paid() * (double) (1)) + (t.getTotal_confirmed() * (50 % 100 * 100));
    }
}


