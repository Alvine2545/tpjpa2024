package entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("STANDARD")
public class TicketStandard extends Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    private int nbrRestant;
    private double discount;
    public TicketStandard() {}
    public TicketStandard(int nbrRestant, int discount) {
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
