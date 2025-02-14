package fr.istic.taa.jaxrs.dao.generic.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.Serializable;

public abstract class GenericDaoImpl <T, PK extends Serializable> implements  GenericDao<T, PK>{
    protected EntityManager entityManager;
    private Class<T> entityClass;

    public GenericDaoImpl(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    /**
     *
     * Create
     * @param t
     * @return
     */
    @Override
    public T save(T t) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(t);
        tx.commit();
        return t;
    }

    /**
     * get/select object
     * @param id
     * @return
     */
    @Override
    public T read(PK id) {
        return entityManager.find(entityClass, id);
    }

    /**
     * Update object with parameters
     * @param t
     * @return
     */
    @Override
    public T update(T t) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        T updatedEntity = entityManager.merge(t);
        tx.commit();
        return updatedEntity;
    }

    /**
     * Delete
     * @param t
     * @return
     */
    @Override
    public T delete(T t) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        if (!entityManager.contains(t)) {
            t = entityManager.merge(t);
        }
        entityManager.remove(t);
        tx.commit();
        return t;
    }

}
