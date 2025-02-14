package fr.istic.taa.jaxrs.dao.generic.DAO;

import java.io.Serializable;

public interface GenericDao<T, PK extends Serializable> {
        T save(T t);
        T read(PK id);
        T update(T t);
        T delete(T t);
}
