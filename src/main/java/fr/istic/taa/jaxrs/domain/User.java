package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements Serializable {

    private Long id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String name_entreprise;
    private int age;

    // Utilisation de Set pour la relation @OneToMany
    private Set<Ticket> tickets = new HashSet<>();

    // Utilisation de Set pour la relation @ManyToMany
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(String name, String password, String email, String phone, String address, String city, int age, Role role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.age = age;
        this.roles.add(role);  // Ajout d'un rôle au moment de la création
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Relation ManyToMany avec Role
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    // Autres getters et setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName_entreprise() {
        return name_entreprise;
    }

    public void setName_entreprise(String name_entreprise) {
        this.name_entreprise = name_entreprise;
    }

    // Relation OneToMany avec Ticket
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    // mappedBy correspond au champ 'user' dans Ticket
    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", city=" + city + ", age=" + age + "]";
    }
}
