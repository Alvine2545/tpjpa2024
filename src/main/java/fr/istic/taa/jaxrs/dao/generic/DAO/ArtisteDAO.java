package fr.istic.taa.jaxrs.dao.generic.DAO;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Artiste;

public class ArtisteDAO extends AbstractJpaDao<Long, Artiste> {
    public ArtisteDAO() {
        super();
        setClazz(Artiste.class);
    }
}
