package com.example.SSH_Init.service;

import com.example.SSH_Init.dao.PermissionDao;
import com.example.SSH_Init.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService extends AbstractHibernateService<Permission> {

    private final PermissionDao permissionDao;

    @Autowired
    public PermissionService(PermissionDao permissionDao) {
        super(permissionDao);
        this.permissionDao = permissionDao;
    }
}
