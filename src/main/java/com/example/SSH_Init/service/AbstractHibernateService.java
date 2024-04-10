package com.example.SSH_Init.service;

import com.example.SSH_Init.dao.AbstractHibernateDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class AbstractHibernateService<T> {

    protected AbstractHibernateDao<T> dao;

    public AbstractHibernateService(AbstractHibernateDao<T> dao) {
        this.dao = dao;
    }

    public List<T> getAll() {
        return dao.getAll();
    }

    public T findById(Long id) {
        return dao.findById(id);
    }

    public void add(T item) {
        dao.add(item);
    }

    public void update(T item) {
        dao.update(item);
    }

    public void delete(T item) {
        dao.delete(item);
    }

    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
