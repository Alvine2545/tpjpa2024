package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Genre implements Serializable {
    private Long id;
    private String name;
    private List<Concert> concerts = new ArrayList<>();

    public Genre() {}
    public Genre(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Concert> getConcerts() {
        return concerts;
    }
    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
    }

}
