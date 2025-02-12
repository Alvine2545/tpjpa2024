package DAO;

import entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpa.EntityManagerHelper;

import java.io.Serializable;

public class UserDao  extends GenericDaoImpl<User,Long>{


    public UserDao(EntityManager entityManager) {
        super(entityManager, User.class);
    }

    /**
     * Get user by email specified on parameter
     * @param email
     * @return
     */
    public User findByEmail(String email) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
