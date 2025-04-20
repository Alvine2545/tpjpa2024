package fr.istic.taa.jaxrs.DTO;

public class TicketPaymentRequest {
    private int quantity;
    private String label;
    private double amount;

    public int getQuantity() {
        return this.quantity;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getLabel() {
        return this.label;
    }

    // getters + setters
}
