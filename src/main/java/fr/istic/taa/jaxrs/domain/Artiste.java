package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Artiste  implements Serializable {

    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String style;

    private Set<Concert> concerts;

    public Artiste() {}
    public Artiste(Long id, String nom, String prenom, String adresse, String style) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.style = style;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getStyle() {
        return style;
    }
    public void setStyle(String style) {
        this.style = style;
    }


    @ManyToMany
    @JoinTable(
            name = "artiste_concert",
            joinColumns = @JoinColumn(name = "artiste_id"),
            inverseJoinColumns = @JoinColumn(name = "concert_id")
    )
    public Set<Concert> getConcerts() {
        return concerts;
    }
    public void setConcerts(Set<Concert> concerts) {
        this.concerts = concerts;
    }

}
