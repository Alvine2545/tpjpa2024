package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Concert implements Serializable {

    private Long id;
    String title;
    String description;
    String location;
    String imagePath;
    Date date;
    Genre genre;

    private Set<Ticket> tickets = new HashSet<Ticket>();

    private Set<Artiste> artistes;

    public Concert() {}
    public Concert(String title, String description, String location, String image, Date date, Genre genre) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.imagePath = image;
        this.date = date;
        this.genre = genre;
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
        this.imagePath = image;
    }
    public String getImage() {
        return imagePath;
    }


    @OneToMany(mappedBy = "concert", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }


    @ManyToMany(mappedBy = "concerts")
    public Set<Artiste> getArtistes() {
        return artistes;
    }
    public void setArtistes(Set<Artiste> artistes) {
        this.artistes = artistes;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "genre_id")
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

}
