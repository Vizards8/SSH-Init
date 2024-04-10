package com.example.SSH_Init.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class AbstractHibernateDao<T> {
    @Autowired
    protected SessionFactory sessionFactory;

    protected Class<T> clazz;

    protected final void setClazz(final Class<T> clazzToSet) {
        clazz = clazzToSet;
    }

    public List<T> getAll() {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(clazz);
        criteria.from(clazz);
        return session.createQuery(criteria).getResultList();
    }

    public T findById(Long id) {
        return getCurrentSession().get(clazz, id);
    }

    public void add(T item) {
        getCurrentSession().save(item);
    }

    public void update(T item) {
        getCurrentSession().update(item);
    }

    public void delete(T item) {
        getCurrentSession().delete(item);
    }

    public void deleteById(Long id) {
        T item = findById(id);
        if (item != null) {
            delete(item);
        }
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
