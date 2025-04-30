package fr.istic.taa.jaxrs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
//@NamedQuery(name = "Concert.findByDate", query = "SELECT c FROM Concert c WHERE c.date = :date")
public class Concert implements Serializable {

    private Long id;
    private String title;
    private String description;
    private String location;
    private String imagePath;
    private Date date;
    private Double price;
    private Integer popularity;
    private Genre genre;
    private Set<Ticket> tickets = new HashSet<>();
    private Set<Artiste> artistes = new HashSet<>();
    private Integer capacity;
    private boolean valide = false;

    public Concert() {}


    public Concert(String title, String description, String location, String image, Date date, Double price, Integer popularity, Integer capacity, Genre genre) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.imagePath = image;
        this.date = date;
        this.price = price;
        this.popularity = popularity;
        this.capacity = capacity;
        this.genre = genre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return imagePath;
    }

    public void setImage(String image) {
        this.imagePath = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
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

    @OneToMany(mappedBy = "concert", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @ManyToMany
    @JoinTable(
            name = "concert_artiste",
            joinColumns = @JoinColumn(name = "concert_id"),
            inverseJoinColumns = @JoinColumn(name = "artiste_id")
    )
    public Set<Artiste> getArtistes() {
        return artistes;
    }

    public void setArtistes(Set<Artiste> artistes) {
        this.artistes = artistes;
    }

    @Override
    public String toString() {
        return "Concert [id=" + id + ", title=" + title + ", date=" + date + ", location=" + location + ", price=" + price + "]";
    }

    @Column(nullable = false)
    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }
}