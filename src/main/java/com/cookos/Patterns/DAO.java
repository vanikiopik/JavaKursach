package com.cookos.Patterns;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import java.util.List;

public class DAO<T> implements AutoCloseable{
    protected static final EntityManagerFactory entityManagerFactory;
    protected final Class<T> type;

    protected EntityManager entityManager = null;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("ZAVOD");
    }

    public DAO(Class<T> type) {
        this.type = type;
        entityManager = entityManagerFactory.createEntityManager();
    }

    public List<T> selectAll() {
        var query = entityManager.getCriteriaBuilder().createQuery(type);
        var root = query.from(type);

        query.select(root);

        var result = entityManager.createQuery(query);
        var selected = result.getResultList();

        return selected;
    }

    public T findByColumn(String columnName, Object value) {
        var cb = entityManager.getCriteriaBuilder();
        var query = cb.createQuery(type);
        var root = query.from(type);

        query.select(root).where(cb.equal(root.get(columnName), value));

        try {
            var result = entityManager.createQuery(query).getSingleResult();
            return result;
        } catch (NoResultException e) {
            return null;
        }
    }

    public void update(T newRecord) {
        entityManager.getTransaction().begin();
        entityManager.merge(newRecord);
        entityManager.getTransaction().commit();
    }

    public void add(T newRecord) throws Exception {
        entityManager.getTransaction().begin();
        entityManager.persist(newRecord);
        entityManager.getTransaction().commit();
    }

    public void remove(T existingRecord) throws Exception {
        entityManager.getTransaction().begin();
        entityManager.remove(existingRecord);
        entityManager.getTransaction().commit();
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
    }
}
