package fr.istic.taa.jaxrs.dao.generic.DAO;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.TicketOffre;

public class TicketOffreDao  extends AbstractJpaDao<Long, TicketOffre> {
    public TicketOffreDao() {
        super();
        setClazz(TicketOffre.class);
    }
}
