package fr.istic.taa.jaxrs.DTO;

import fr.istic.taa.jaxrs.domain.Concert;
import fr.istic.taa.jaxrs.domain.Genre;

import java.util.Date;
import java.util.Set;

public class ConcertDto {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String imagePath;
    private int nbrTicket;
    private Set<Long> artisteIds;
    private Genre genre;
    private Date date;


    public ConcertDto(Long id, String title, String description, String location, String imagePath, Integer capacity, Genre genre) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.imagePath = imagePath;
        this.genre = genre;
        this.nbrTicket = capacity;
    }
    public ConcertDto(Concert concert) {
        this.id = concert.getId();
        this.title = concert.getTitle();
        this.description = concert.getDescription();
        this.location = concert.getLocation();
        this.imagePath = concert.getImage();
        this.genre = concert.getGenre();
        this.nbrTicket = concert.getCapacity();
        this.date = concert.getDate();
    }


    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getImagePath() { return imagePath; }
    public int getNbrTicket() { return nbrTicket; }
    public Set<Long> getArtisteIds() { return artisteIds; }
    public String getGenreName() { return genre.getName(); }
    public Date getDate() { return date; }
}
