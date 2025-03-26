package fr.istic.taa.jaxrs.DTO;

import fr.istic.taa.jaxrs.domain.Role;

public class RoleDto {
    private Long id;
    private String name;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }
}
