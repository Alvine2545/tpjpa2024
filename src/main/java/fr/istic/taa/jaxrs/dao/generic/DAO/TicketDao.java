package fr.istic.taa.jaxrs.dao.generic.DAO;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.User;

public class TicketDao extends AbstractJpaDao<Long, Ticket> {
    public TicketDao() {
        super();
        setClazz(Ticket.class);
    }

}
