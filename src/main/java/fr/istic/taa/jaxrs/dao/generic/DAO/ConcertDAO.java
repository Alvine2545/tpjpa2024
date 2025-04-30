package fr.istic.taa.jaxrs.dao.generic.DAO;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.dao.generic.EntityManagerHelper;
import fr.istic.taa.jaxrs.domain.Concert;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConcertDAO extends AbstractJpaDao<Long, Concert> {
    public ConcertDAO() {
        super();
        setClazz(Concert.class);
    }

    public List<Concert> findConcertsByPriceRange(Double minPrice, Double maxPrice) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Concert> query = cb.createQuery(Concert.class);
        Root<Concert> concert = query.from(Concert.class);
        query.select(concert).where(cb.between(concert.get("price"), minPrice, maxPrice));
        return entityManager.createQuery(query).getResultList();
    }

    public List<Concert> searchConcerts(String artistName, String location, Date date, Long genreId, String sortBy) {
        StringBuilder jpql = new StringBuilder("SELECT DISTINCT c FROM Concert c LEFT JOIN c.artistes a LEFT JOIN c.tickets t WHERE 1=1");


        if (artistName != null && !artistName.isEmpty()) {
            System.out.println(jpql);
            jpql.append(" AND LOWER(a.nom) LIKE :artistName");
        }
        if (location != null && !location.isEmpty()) {
            jpql.append(" AND LOWER(c.location) LIKE :location");
        }
        if (date != null) {
            jpql.append(" AND FUNCTION('DATE', c.date) = :date");
        }
        if (genreId != null) {
            jpql.append(" AND c.genre.id = :genreId");
        }

        if (sortBy != null) {
            if (sortBy.equals("date")) {
                jpql.append(" ORDER BY c.date ASC");
            } else if (sortBy.equals("price")) {
                jpql.append(" ORDER BY MIN(t.price) ASC");
            } else if (sortBy.equals("popularité")) {
                jpql.append(" ORDER BY SIZE(c.tickets) DESC");
            }
        }

        TypedQuery<Concert> query = entityManager.createQuery(jpql.toString(), Concert.class);

        if (artistName != null && !artistName.isEmpty()) {
            query.setParameter("artistName", "%" + artistName.toLowerCase() + "%");
        }
        if (location != null && !location.isEmpty()) {
            query.setParameter("location", "%" + location.toLowerCase() + "%");
        }
        if (date != null) {
            query.setParameter("date", date);
        }
        if (genreId != null) {
            query.setParameter("genreId", genreId);
        }
        return query.getResultList();
    }

    public List<Concert> getConcertsNonValides() {
        return entityManager.createQuery("SELECT c FROM Concert c WHERE c.valide = false", Concert.class)
                .getResultList();
    }


}
