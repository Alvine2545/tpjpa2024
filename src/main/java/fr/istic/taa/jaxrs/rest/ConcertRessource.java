package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.DTO.ConcertDto;
import fr.istic.taa.jaxrs.dao.generic.DAO.ConcertDAO;
import fr.istic.taa.jaxrs.domain.Concert;
import fr.istic.taa.jaxrs.domain.Role;
import fr.istic.taa.jaxrs.domain.User;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/concerts")
@Produces({"application/json", "application/xml"})
public class ConcertRessource {
    private ConcertDAO concertDao = new ConcertDAO();
    @GET
    @Path("/{concertId}")
    public Response getConcertById(@PathParam("concertId") Long concertId)  {
        Concert concert = concertDao.findOne(concertId);
        if (concert == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Concert not found").build();
        }
        return Response.ok(new ConcertDto(concert)).build();
    }

    @GET
    @Path("/")
    public Response getAllConcerts() {
        // On récupère tous les rôles depuis le DAO
        List<Concert> concerts = concertDao.findAll();
        if (concerts == null || concerts.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(concerts).build();
    }

    @POST
    @Consumes("application/json")
    public Response addConcert(@Parameter(description = "User object that needs to be added to the store", required = true) Concert concert) {
        //User user = new User(userDTO.getNom(), userDTO.getEmail());
        System.out.println("Requête POST reçue : " + concert);
        concertDao.save(concert);
        return Response.ok().entity("SUCCESS").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteConcert(@PathParam("id") Long id) {
        Concert concert = concertDao.findOne(id);
        if (concert == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Concert not found").build();
        }
        concertDao.delete(concert);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    /*@PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id, UserDto userDTO) {
        User existingUser = userDao.findOne(id);
        if (existingUser == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Utilisateur non trouvé").build();
        }
        existingUser.setNom(userDTO.getNom());
        existingUser.setEmail(userDTO.getEmail());
        userDao.update(existingUser);
        return Response.ok(new UserDto(existingUser)).build();
    }*/
}
