package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.DTO.StatsDto;
import fr.istic.taa.jaxrs.dao.generic.DAO.ConcertDAO;
import fr.istic.taa.jaxrs.dao.generic.DAO.TicketDao;
import fr.istic.taa.jaxrs.domain.Concert;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/stats")
@Produces(MediaType.APPLICATION_JSON)
public class StatResource {

    //@Inject
    private TicketDao ticketDao;

    //@Inject
    private ConcertDAO concertDao;

    @GET
    @Path("/concert/{id}")
    public StatsDto getConcertStats(@PathParam("id") Long concertId) {
        long sold = ticketDao.countTicketsByConcert(concertId);
        double revenue = ticketDao.sumRevenueByConcert(concertId);
        Concert concert = concertDao.findOne(concertId);
        int capacity = concert != null ? concert.getCapacity() : 0;

        return new StatsDto(sold, revenue, capacity);
    }
}
