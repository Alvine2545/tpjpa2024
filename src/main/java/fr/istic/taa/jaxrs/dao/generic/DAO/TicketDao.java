package fr.istic.taa.jaxrs.dao.generic.DAO;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.User;
import jakarta.persistence.TypedQuery;

public class TicketDao extends AbstractJpaDao<Long, Ticket> {
    public TicketDao() {
        super();
        setClazz(Ticket.class);
    }

    public long countTicketsByConcert(Long concertId) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(t) FROM Ticket t WHERE t.concert.id = :concertId", Long.class);
        query.setParameter("concertId", concertId);
        return query.getSingleResult();
    }

    public double sumRevenueByConcert(Long concertId) {
        TypedQuery<Double> query = entityManager.createQuery(
                "SELECT COALESCE(SUM(CAST(t.price AS double)), 0) FROM Ticket t WHERE t.concert.id = :concertId", Double.class);
        query.setParameter("concertId", concertId);
        return query.getSingleResult();
    }


}
