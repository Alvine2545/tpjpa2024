package DAO;

import entities.Role;
import jakarta.persistence.EntityManager;

public class RoleDao extends GenericDaoImpl<Role, String>{
    public RoleDao(EntityManager entityManager) {
        super(entityManager, Role.class);
    }
}
