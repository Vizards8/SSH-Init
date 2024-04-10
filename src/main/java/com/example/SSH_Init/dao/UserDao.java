package com.example.SSH_Init.dao;

import com.example.SSH_Init.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDao extends AbstractHibernateDao<User> {

    public UserDao() {
        setClazz(User.class);
    }

    public User findByEmail(String email) {
        Query<User> query = getCurrentSession().createQuery("FROM User WHERE email = :email", User.class);
        query.setParameter("email", email);
        return query.uniqueResult();
    }

    public Optional<User> loadUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User WHERE username = :username", User.class);
        query.setParameter("username", username);
        User user = (User) query.getResultList().get(0);
        return Optional.ofNullable(user);
    }

    public User findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User WHERE username = :username", User.class);
        query.setParameter("username", username);
        User user = (User) query.uniqueResult();
        return user;
    }
}
