package entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("LAST_MINUTE")
public class TicketLastMinute extends Ticket {

    Long id;
    private int nbrRestant;
    public TicketLastMinute() {}
    public TicketLastMinute(int nbrRestant) {
        this.nbrRestant = nbrRestant;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    @Override
    public String toString() {
        return super.toString() + " [Nombre de place: restantes=" + nbrRestant + "]";
    }
}
