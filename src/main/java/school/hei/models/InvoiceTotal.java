package school.hei;

import school.hei.enums.Status;

public class InvoiceTotal {
    private int id;
    private String customerName;
    private Status status;
    private Double amount;

    public InvoiceTotal() {
    }

    public InvoiceTotal(int id, String customerName, Status status, Double amount) {
        this.id = id;
        this.customerName = customerName;
        this.status = status;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("%d | %s | %s | %.2f",
                id, customerName, status, amount != null ? amount : 0.0);
    }
}