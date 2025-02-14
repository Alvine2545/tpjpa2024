package fr.istic.taa.jaxrs.DTO;

import java.util.Date;

public class TicketDto {
    private Long id;
    private String statut;
    private String description;
    private Date date;
    private String place;
    private String concertTitle; // Nom du concert (au lieu d'un objet complet)
    private String userName;     // Nom de l'utilisateur (au lieu d'un objet complet)

    public TicketDto(Long id, String statut, String description, Date date, String place, String concertTitle, String userName) {
        this.id = id;
        this.statut = statut;
        this.description = description;
        this.date = date;
        this.place = place;
        this.concertTitle = concertTitle;
        this.userName = userName;
    }
}
