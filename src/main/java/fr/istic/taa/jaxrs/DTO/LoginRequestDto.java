package fr.istic.taa.jaxrs.DTO;

public class LoginRequestDto {
    private String username;
    private String password;

    public LoginRequestDto() {} // Obligatoire pour JAX-RS

    public LoginRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters aussi si tu utilises un framework comme Jackson
}
