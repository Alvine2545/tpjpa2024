package fr.istic.taa.jaxrs.dao.generic.DAO;

import fr.istic.taa.jaxrs.DTO.UserDto;
import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.User;
import jakarta.persistence.EntityManager;

public class UserDao  extends AbstractJpaDao<Long, User> {
    public UserDao() {
        super();
        setClazz(User.class);
    }


}
