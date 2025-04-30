package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Une seule table pour tous les types de tickets
@DiscriminatorColumn(name = "ticket_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Ticket implements Serializable {
    private Long id;
    String statut;
    String description;
    Date purchaseDate;
    String place;
    int nbr_ticket;
    String capacity;
    String price;
    Concert concert;
    User user;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }
    public String getStatut() {
        return statut;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public void setDate(Date date) {
        this.purchaseDate = date;
    }
    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return purchaseDate;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public String getPlace() {
        return place;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }

    @ManyToOne
    @JoinColumn(name = "concert_id")
    public Concert getConcert() {
        return concert;
    }
    public void setUser(User user) {
        this.user = user;
    }
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Ticket [id=" + id + ", content=" + description + ", place="
                + place + "]";
    }

    @Transient
    public String getType() {
        return this.getClass().getSimpleName();
    }


}
