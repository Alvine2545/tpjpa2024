package fr.istic.taa.jaxrs.DTO;

import fr.istic.taa.jaxrs.domain.Concert;

public class ConcertDto {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String price;
    private int nbrTicket;

    public ConcertDto(Long id, String title, String description, String location, String price, int nbrTicket) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.price = price;
        this.nbrTicket = nbrTicket;
    }
    public ConcertDto(Concert concert) {
        this.id = concert.getId();
        this.title = concert.getTitle();
        this.description = concert.getDescription();
        this.location = concert.getLocation();
        this.price = concert.getPrice();
        this.nbrTicket = concert.getNbr_ticket();
    }
}
