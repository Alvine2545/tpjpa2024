package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {

    private Long id; // Utilisation de Long (objet) pour correspondre à l'ID JPA
    private String name;

    // Utilisation de HashSet standard de Java
    private Set<User> users = new HashSet<>();

    // Constructeur par défaut
    public Role() {
        super();
    }

    // Constructeur avec le nom
    public Role(String name) {
        super();
        this.name = name;
    }

    // Getter et Setter pour name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter et Setter pour id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter et Setter pour users
    @ManyToMany(mappedBy = "roles") // mappedBy doit correspondre à l'attribut dans User
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
