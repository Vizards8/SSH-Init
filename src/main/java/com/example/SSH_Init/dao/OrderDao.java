package com.example.SSH_Init.dao;

import com.example.SSH_Init.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao extends AbstractHibernateDao<Order> {

    public OrderDao() {
        setClazz(Order.class);
    }
}
