package fr.istic.taa.jaxrs.dao.generic.DAO;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.dao.generic.EntityManagerHelper;
import fr.istic.taa.jaxrs.domain.Concert;
import jakarta.persistence.EntityTransaction;

import java.util.Date;

public class ConcertDAO extends AbstractJpaDao<String, Concert> {
    public ConcertDAO() {
        super();
        setClazz(Concert.class);
    }
}
