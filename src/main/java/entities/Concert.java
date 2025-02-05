package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

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

}
