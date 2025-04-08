package fr.istic.taa.jaxrs.dao.generic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractJpaDao<K, T extends Serializable> implements IGenericDao<K, T> {

	private Class<T> clazz;

	protected EntityManager entityManager;

	public AbstractJpaDao() {
		this.entityManager = EntityManagerHelper.getEntityManager();
	}

	public void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public T findOne(K id) {
		return entityManager.find(clazz, id);
	}

	public List<T> findAll() {
		return entityManager.createQuery("select e from " + clazz.getName() + " as e",clazz).getResultList();
	}

	public void save(T entity) {
		EntityTransaction t = this.entityManager.getTransaction();
		t.begin();
		entityManager.persist(entity);
		t.commit();

	}

	public T update(final T entity) {
		EntityTransaction t = this.entityManager.getTransaction();
		t.begin();
		T res = entityManager.merge(entity);
		t.commit();
		return res;

	}

	public void delete(T entity) {
		EntityTransaction t = this.entityManager.getTransaction();
		t.begin();
		entityManager.remove(entity);
		t.commit();

	}

	public void deleteById(K entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}

	public T findByEmail(String email) {
		try {

			return entityManager.createQuery(
							"SELECT u FROM " + clazz.getSimpleName() + " u WHERE u.email = :email", clazz)
					.setParameter("email", email)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
