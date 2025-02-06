package entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Ticket {
    private Long id;
    String statut;
    String description;
    Date date;
    String place;
    Concert concert;
    User user;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
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
        this.date = date;
    }
    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return date;
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


}
