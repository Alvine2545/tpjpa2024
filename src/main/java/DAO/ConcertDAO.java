package DAO;

import entities.Concert;
import jakarta.persistence.EntityTransaction;
import jpa.EntityManagerHelper;

import java.io.Serializable;
import java.util.Date;

public class ConcertDAO implements GenericDao<Concert, String> {
    /**
     * Create
     * @param concert
     * @return
     */
    @Override
    public Concert save(Concert concert) {
        EntityTransaction tx = EntityManagerHelper.getEntityManager().getTransaction();
        tx.begin();
        EntityManagerHelper.getEntityManager().persist(concert);
        tx.commit();
        return concert;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Concert read(String id) {
        return null;
    }

    /**
     * Update
     * @param concert
     * @return
     */
    @Override
    public Concert update(Concert concert) {
        EntityTransaction tx = EntityManagerHelper.getEntityManager().getTransaction();
        tx.begin();
        EntityManagerHelper.getEntityManager().merge(concert);
        return null;
    }

    /**
     * Add details
     * @param
     * @return Concert with details
     */
    public Concert addDetails(Date date, String lieu, String description, Long price, int capacity) {
        Concert concert = new Concert();
        this.update(concert);
        return null;
    }

    /**
     * Add details
     * @param
     * @return Concert with details
     */
    public void stock() {
        Concert concert = new Concert();

    }

    /**
     * Deelete concert/event
     * @param concert
     */
    @Override
    public Concert delete(Concert concert) {
        EntityTransaction tx = EntityManagerHelper.getEntityManager().getTransaction();
        tx.begin();
        EntityManagerHelper.getEntityManager().remove(concert);
        tx.commit();
        return concert;
    }

}
