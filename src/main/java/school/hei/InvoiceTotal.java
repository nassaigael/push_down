package school.hei;

import java.util.Objects;

public class InvoiceTotal {
    private int id;
    private String customerName;
    private Status status;
    private Double amount;

    public InvoiceTotal() {
    }

    public InvoiceTotal(int id, String customerName, Double amount) {
        this.id = id;
        this.customerName = customerName;
        this.amount = amount;
    }

    public Double getAmount(double amount) {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "InvoiceTotal{" + "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", status=" + status +
                ", amount=" + amount +
                '}';
    }
}
