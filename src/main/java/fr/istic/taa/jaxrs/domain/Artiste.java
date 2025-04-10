package fr.istic.taa.jaxrs.domain;

public class Artiste {
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    public Artiste(Long id, String nom, String prenom, String adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;

    }
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

}
