package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Entity
public class TicketOffre implements Serializable {

    private Long id;

    private String type; // VIP, STANDARD, LastMinute.
    private Double price;
    private int quantityAvailable;
    private String description;


    private Concert concert;
    public TicketOffre() {

    }
    public TicketOffre(String type, Double price, int quantityAvailable, String description, Concert concert) {
        this.type = type;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
        this.concert = concert;
        this.description = description;

    }
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public int getQuantityAvailable() {
        return quantityAvailable;
    }
    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
    @ManyToOne
    public Concert getConcert() {
        return concert;
    }
    public void setConcert(Concert concert) {
        this.concert = concert;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}