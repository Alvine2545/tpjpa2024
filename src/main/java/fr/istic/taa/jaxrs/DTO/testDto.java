package fr.istic.taa.jaxrs.DTO;
import fr.istic.taa.jaxrs.domain.Concert;
import fr.istic.taa.jaxrs.domain.Role;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.User;

import java.util.stream.Collectors;
public class testDto {



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

        public static RoleDto toRoleDTO(Role role) {
            return new RoleDto(role.getId(), role.getName());
        }

        public static TicketDto toTicketDTO(Ticket ticket) {
            return new TicketDto(
                    ticket.getId(),
                    ticket.getStatut(),
                    ticket.getDescription(),
                    ticket.getDate(),
                    ticket.getPlace(),
                    ticket.getConcert() != null ? ticket.getConcert().getTitle() : null, // Vérifie si concert existe
                    ticket.getUser() != null ? ticket.getUser().getName() : null // Vérifie si user existe
            );
        }

        public static ConcertDto toConcertDTO(Concert concert) {
            return new ConcertDto(
                    concert.getId(),
                    concert.getTitle(),
                    concert.getDescription(),
                    concert.getLocation(),
                    concert.getPrice(),
                    concert.getNbr_ticket()
            );
        }




}
