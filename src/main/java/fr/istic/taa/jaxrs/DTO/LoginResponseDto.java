package fr.istic.taa.jaxrs.DTO;

public class LoginResponseDto {
    private String token;

    public LoginResponseDto() {}

    public LoginResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    // Setter si besoin
}
