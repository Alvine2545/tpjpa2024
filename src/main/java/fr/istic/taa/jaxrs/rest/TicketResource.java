package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.DTO.TicketDto;
import fr.istic.taa.jaxrs.DTO.TicketPurchaseDTO;
import fr.istic.taa.jaxrs.dao.generic.DAO.TicketDao;
import fr.istic.taa.jaxrs.dao.generic.DAO.TicketOffreDao;
import fr.istic.taa.jaxrs.dao.generic.DAO.UserDao;
import fr.istic.taa.jaxrs.domain.*;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;


// Stripe integration

import java.util.Date;
import java.util.List;


@Path("/tickets")
@Produces({"application/json", "application/xml"})
public class TicketResource {

    private TicketDao ticketDao = new TicketDao();
    private TicketOffreDao ticketOffreDao = new TicketOffreDao();
    private UserDao userDao = new UserDao();

    @GET
    @Path("/offres")
    public Response getTicketOffers() {

        List<TicketOffre> offers = ticketOffreDao.findAll();
        if (offers == null || offers.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        return Response.ok(offers).build();
    }

    @POST
    @Consumes("application/json")
    @Path("/add/offres")
    public Response addOffres( TicketOffre offre) {
        System.out.println("Requête POST reçue : " + offre);
        ticketOffreDao.save(offre);
        return Response.ok().entity("SUCCESS").build();
    }

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

    @POST
    @Path("/purchase")
    public Response purchaseTicket(TicketPurchaseDTO dto) {
        TicketOffre offer = ticketOffreDao.findOne(dto.offerId);
        if (offer == null || offer.getQuantityAvailable() <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("No more tickets available").build();
        }

        Ticket ticket;
        if ("PREMIUM".equalsIgnoreCase(offer.getType())) {

            ticket = new TicketPremium();
        } else if ("STANDARD".equalsIgnoreCase(offer.getType())){
            ticket = new TicketStandard();
        }else{
            ticket = new TicketLastMinute();
        }

        ticket.setConcert(offer.getConcert());
        ticket.setUser(userDao.findOne(dto.userId));
        ticket.setPlace(dto.seat);
        ticket.setDate(new Date());
        ticket.setPrice(offer.getPrice());
        ticket.setStatut("VALID");

        ticketDao.save(ticket);

        offer.setQuantityAvailable(offer.getQuantityAvailable() - dto.quantity);
       // ticketOffreDao.updateOffer(offer);

        return Response.ok(ticket).build();
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

    /*@Path("/{id}/status")
    public ResponseEntity<Ticket> updateTicketStatus(
           Long ticketId, Map<String, String> statusUpdate) {

        Ticket updatedTicket = ticketService.updateStatus(
                ticketId,
                statusUpdate.get("status")
        );
        return ResponseEntity.ok(updatedTicket);
    }*/




}
