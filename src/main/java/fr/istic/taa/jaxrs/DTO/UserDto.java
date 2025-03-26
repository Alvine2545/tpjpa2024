package fr.istic.taa.jaxrs.DTO;

import fr.istic.taa.jaxrs.domain.Role;
import fr.istic.taa.jaxrs.domain.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String city;
    private int age;
    private Set<String> roles; // On ne met que les noms des rôles

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.city = user.getCity();
        this.age = user.getAge();
        this.roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
    }
    public UserDto(Long id, String name, String email, String phone, String city, int age, Set<String> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.age = age;
        this.roles = roles;
    }
    public static UserDto toUserDTO(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getCity(),
                user.getAge(),
                user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()) // Extrait les noms des rôles
        );
    }
}
