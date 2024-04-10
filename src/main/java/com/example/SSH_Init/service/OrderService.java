package com.example.SSH_Init.service;

import com.example.SSH_Init.dao.OrderDao;
import com.example.SSH_Init.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends AbstractHibernateService<Order> {

    private final OrderDao orderDao;

    @Autowired
    public OrderService(OrderDao orderDao) {
        super(orderDao);
        this.orderDao = orderDao;
    }
}
