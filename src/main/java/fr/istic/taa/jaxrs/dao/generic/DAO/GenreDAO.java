package fr.istic.taa.jaxrs.dao.generic.DAO;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Genre;
import fr.istic.taa.jaxrs.domain.Role;

public class GenreDAO extends AbstractJpaDao<Long, Genre> {
    public GenreDAO() {
        super();
        setClazz(Genre.class);
    }
}
