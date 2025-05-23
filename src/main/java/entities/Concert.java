package entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Concert {

    private Long id;
    String title;
    String description;
    String location;
    String image;
    int nbr_ticket;
    String capacity;
    String price;
    private Set<Ticket> tickets = new HashSet<Ticket>();

    public Concert() {}
    public Concert(String title, String description, String location, String image, int nbr_ticket, String capacity, String price) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.image = image;
        this.nbr_ticket = nbr_ticket;
        this.capacity = capacity;
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
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getImage() {
        return image;
    }
    public void setNbr_ticket(int nbr_ticket) {
        this.nbr_ticket = nbr_ticket;
    }
    public int getNbr_ticket() {
        return nbr_ticket;
    }
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
    public String getCapacity() {
        return capacity;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getPrice() {
        return price;
    }
    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
    //@OneToMany
    @OneToMany(mappedBy = "concert", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Ticket> getTickets() {
        return tickets;
    }
}
