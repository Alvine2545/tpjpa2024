package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.DTO.LoginRequestDto;
import fr.istic.taa.jaxrs.DTO.LoginResponseDto;
import fr.istic.taa.jaxrs.DTO.TicketDto;
import fr.istic.taa.jaxrs.DTO.UserDto;
import fr.istic.taa.jaxrs.dao.generic.DAO.TicketDao;
import fr.istic.taa.jaxrs.dao.generic.DAO.UserDao;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.User;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/tickets")
@Produces({"application/json", "application/xml"})
public class TicketResource {

    private TicketDao ticketDao = new TicketDao();
    @GET
    @Path("/{ticketId}")
    public Response getTicketById(@PathParam("ticketId") Long ticketId)  {
        Ticket ticket = ticketDao.findOne(ticketId);
        if (ticket == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Ticket not found").build();
        }
        return Response.ok(new TicketDto(ticket)).build();
    }

    @POST
    @Consumes("application/json")
    public Response addTicket(@Parameter(description = "User object that needs to be added to the store", required = true) Ticket ticket) {
        System.out.println("Requête POST reçue : " + ticket);
        ticketDao.save(ticket);
        return Response.ok().entity("SUCCESS").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTicket(@PathParam("id") Long id) {
        Ticket ticket = ticketDao.findOne(id);
        if (ticket == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Ticket not found").build();
        }
        ticketDao.delete(ticket);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    /*@PUT
    @Path("/{id}")
    public Response updateTicket(@PathParam("id") Long id, TicketDto ticketDTO) {
        Ticket existingTicket = ticketDao.findOne(id);
        if (existingTicket == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Ticket non trouvé").build();
        }
        existingTicket.setNom(userDTO.getNom());
        existingTicket.setEmail(userDTO.getEmail());
        ticketDao.update(existingTicket);
        return Response.ok(new TicketDto(existingTicket)).build();
    }*/


}
