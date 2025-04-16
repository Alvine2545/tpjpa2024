package fr.istic.taa.jaxrs.DTO;

import fr.istic.taa.jaxrs.domain.Artiste;
import fr.istic.taa.jaxrs.domain.Concert;

import java.util.Set;
import java.util.stream.Collectors;

public class ArtisteDto {
    private Long id;
    private String nom;
    private String style;
    private Set<Long> concertIds;

    public ArtisteDto() {}
    public ArtisteDto(Artiste artiste) {
        this.id = artiste.getId();
        this.nom = artiste.getNom() + " " + artiste.getPrenom();
        this.style = artiste.getStyle();
        this.concertIds = artiste.getConcerts().stream()
                .map(Concert::getId)
                .collect(Collectors.toSet());;

    }
}
