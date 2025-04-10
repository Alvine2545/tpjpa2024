package fr.istic.taa.jaxrs.DTO;

import fr.istic.taa.jaxrs.domain.Role;
import fr.istic.taa.jaxrs.domain.Ticket;

import java.util.Date;
import java.util.stream.Collectors;

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
    public TicketDto(Ticket ticket) {
        this.id = ticket.getId();
        this.statut = ticket.getStatut();
        this.description = ticket.getDescription();
        this.date = ticket.getDate();
        this.place = ticket.getPlace();
        this.concertTitle = ticket.getConcert().getTitle();
        this.userName = ticket.getUser().getName();
        //this.roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());

    }
}
