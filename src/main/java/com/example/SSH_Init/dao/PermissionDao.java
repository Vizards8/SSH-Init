package com.example.SSH_Init.dao;

import com.example.SSH_Init.entity.Permission;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDao extends AbstractHibernateDao<Permission> {

    public PermissionDao() {
        setClazz(Permission.class);
    }
}
