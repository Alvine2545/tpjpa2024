package entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("LAST_MINUTE")
public class TicketPremium extends Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    private int nbrRestant;
    private double discount;
    public TicketPremium() {}
    public TicketPremium(int nbrRestant, int discount) {
        this.nbrRestant = nbrRestant;
        this.discount = discount;
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
}
