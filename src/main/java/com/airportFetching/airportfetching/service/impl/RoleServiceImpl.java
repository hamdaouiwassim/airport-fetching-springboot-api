package com.airportFetching.airportfetching.service.impl;

import com.airportFetching.airportfetching.dao.RoleDao;
import com.airportFetching.airportfetching.model.Role;
import com.airportFetching.airportfetching.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findByName(String name) {
        Role role = roleDao.findRoleByName(name);
        return role;
    }
}
