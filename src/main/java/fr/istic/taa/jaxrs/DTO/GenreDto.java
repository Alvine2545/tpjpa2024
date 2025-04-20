package fr.istic.taa.jaxrs.DTO;

import fr.istic.taa.jaxrs.domain.Genre;
import fr.istic.taa.jaxrs.domain.Role;

public class GenreDto {
    private Long id;
    private String name;

    public GenreDto(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
    }
    public GenreDto() {}
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
}
