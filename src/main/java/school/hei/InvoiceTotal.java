package school.hei;

import java.util.Objects;

public class InvoiceTotal {
    private int id;
    private String customer_name;
    private Status status;

    public InvoiceTotal() {
    }

    public InvoiceTotal(int id, String customer_name, Status status) {
        this.id = id;
        this.customer_name = customer_name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof InvoiceTotal that)) return false;
        return getId() == that.getId() && Objects.equals(getCustomer_name(), that.getCustomer_name()) && getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomer_name(), getStatus());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InvoiceTotal{");
        sb.append("id=").append(id);
        sb.append(", customer_name='").append(customer_name).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
