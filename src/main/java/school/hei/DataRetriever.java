package school.hei;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataRetreiver {
    private final DBConnection dbConnection;

    public DataRetreiver(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<InvoiceTotal> findInvoicesTotal() {
        List<InvoiceTotal> invoiceTotals = new ArrayList<>();
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
            while (r.next()){
                InvoiceTotal i = new InvoiceTotal();
                i.setId(r.getInt(1));
                i.setCustomer_name();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
