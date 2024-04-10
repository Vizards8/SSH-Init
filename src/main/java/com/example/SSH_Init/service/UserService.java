package com.example.SSH_Init.service;

import com.example.SSH_Init.dao.UserDao;
import com.example.SSH_Init.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractHibernateService<User> {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
