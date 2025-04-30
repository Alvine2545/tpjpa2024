package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.DTO.ConcertDto;
import fr.istic.taa.jaxrs.dao.generic.DAO.ConcertDAO;
import fr.istic.taa.jaxrs.domain.Concert;
import fr.istic.taa.jaxrs.domain.Role;
import fr.istic.taa.jaxrs.domain.User;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.File;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        List<ConcertDto> concertDtos = concerts.stream()
                .map(ConcertDto::new)
                .collect(Collectors.toList());

        return Response.ok(concertDtos).build();
    }

    @GET
    public List<Concert> getConcerts(@QueryParam("valide") Boolean valide) {
        if (valide != null && !valide) {
            return concertDao.getConcertsNonValides();
        }
        return concertDao.findAll();
    }

    @POST
    @Consumes("application/json")
    public Response addConcert(@Parameter(description = "User object that needs to be added to the store", required = true) Concert concert) {
        //User user = new User(userDTO.getNom(), userDTO.getEmail());
        System.out.println("Requête POST reçue : " + concert);
        //concert.setImage("festival2024.jpg");
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

    @GET
    @Path("images/{filename}")
    @Produces("image/png")
    public Response getImage(@PathParam("filename") String filename) {
        File file = new File("uploads/concerts/" + filename);
        if (!file.exists()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(file).build();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchConcerts(@QueryParam("artiste") String artiste,
                                   @QueryParam("lieu") String lieu,
                                   @QueryParam("date") Date date,
                                   @QueryParam("genre") Long genre,
                                   @QueryParam("sort") String sort) {

        List<Concert> result = concertDao.searchConcerts(artiste, lieu, date, genre, sort);
        List<ConcertDto> concertDtos = result.stream()
                .map(ConcertDto::new)
                .collect(Collectors.toList());
        return Response.ok(concertDtos).build();
    }

    @PUT
    @Path("/{id}")
    public Concert updateConcert(@PathParam("id") Long id, Concert updatedConcert) {
        Concert existing = concertDao.findOne(id);
        if (existing == null) throw new WebApplicationException("Concert not found", 404);

        existing.setTitle(updatedConcert.getTitle());
        existing.setDescription(updatedConcert.getDescription());
        existing.setDate(updatedConcert.getDate());
        existing.setLocation(updatedConcert.getLocation());
        existing.setPrice(updatedConcert.getPrice());
        existing.setImage(updatedConcert.getImage());
        existing.setPopularity(updatedConcert.getPopularity());
        existing.setGenre(updatedConcert.getGenre());

        return concertDao.update(existing);
    }

    @GET
    @Path("/{id}/stock")
    public int getAvailableTickets(@PathParam("id") Long id) {
        Concert concert = concertDao.findOne(id);
        return concert.getCapacity() - concert.getTickets().size();
    }

   /* @POST
    @Path("/{id}/ticket")
    public int addTicketsToConcert(@PathParam("id") Long id) {
        if (concert.getTickets().size() >= concert.getCapacity()) {
            throw new WebApplicationException("Complet", 400);
        }

    }*/

    @PUT
    @Path("/{id}/valider")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validerConcert(@PathParam("id") Long id) {
        Concert concert = concertDao.findOne(id);
        if (concert == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        concert.setValide(true);
        concertDao.save(concert);
        return Response.ok().build();
    }

}
