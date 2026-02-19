package school.hei;

public class InvoiceStatusTotals {
    private Double total_paid;
    private Double total_confirmed;
    private Double total_draft;

    public InvoiceStatusTotals() {

    }

    public InvoiceStatusTotals(Double total_paid, Double total_confirmed, Double total_draft) {
        this.total_paid = total_paid;
        this.total_confirmed = total_confirmed;
        this.total_draft = total_draft;
    }

    public Double getTotal_paid() {
        return total_paid;
    }

    public void setTotal_paid(Double total_paid) {
        this.total_paid = total_paid;
    }

    public Double getTotal_confirmed() {
        return total_confirmed;
    }

    public void setTotal_confirmed(Double total_confirmed) {
        this.total_confirmed = total_confirmed;
    }

    public Double getTotal_draft() {
        return total_draft;
    }

    public void setTotal_draft(Double total_draft) {
        this.total_draft = total_draft;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InvoiceStatusTotals{");
        sb.append("total_paid=").append(total_paid);
        sb.append(", total_confirmed=").append(total_confirmed);
        sb.append(", total_draft=").append(total_draft);
        sb.append('}');
        return sb.toString();
    }


}
