package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


public class TicketLastMinute extends Ticket {

    Long id;
    private int nbrRestant;
    private double price;
    public TicketLastMinute() {}
    public TicketLastMinute(int nbrRestant, double price) {
        this.nbrRestant = nbrRestant;
        this.price = price;
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


    public int getNbrRestant() {
        return nbrRestant;
    }
    public void setNbrRestant(int nbrRestant) {
        this.nbrRestant = nbrRestant;
    }


}
