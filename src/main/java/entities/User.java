package entities;

import jakarta.persistence.*;

@Entity
public class User {

    long id;
    String name;
    String password;
    String email;
    String phone;
    String address;
    String city;
    String name_entreprise;
    int age;

    Role role;

    @ManyToOne
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User() {}
    public User(String name, String password, String email, String phone, String address, String city, int age, Role
            role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.age = age;
        this.role = role;
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
}
