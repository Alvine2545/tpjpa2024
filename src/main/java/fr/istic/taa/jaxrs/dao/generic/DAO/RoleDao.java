package fr.istic.taa.jaxrs.dao.generic.DAO;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Role;
import fr.istic.taa.jaxrs.domain.User;
import jakarta.persistence.EntityManager;

public class RoleDao extends AbstractJpaDao<Long, Role> {

    public RoleDao() {
        super();
        setClazz(Role.class);
    }
}
