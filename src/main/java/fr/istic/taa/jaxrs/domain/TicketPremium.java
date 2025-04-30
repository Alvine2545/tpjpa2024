package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PREMIUM")
public class TicketPremium extends Ticket {

    Long id;
    private int nbrRestant;
    private double discount;
    private double price;

    public TicketPremium() {}

    public TicketPremium(int nbrRestant, int discount, double price) {
        this.nbrRestant = nbrRestant;
        this.discount = discount;
        this.price = price;
    }
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return super.toString() + " [Réduction: price=" + discount + " %]";
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getNbrRestant() {
        return nbrRestant;
    }
    public void setNbrRestant(int nbrRestant) {
        this.nbrRestant = nbrRestant;
    }


}
