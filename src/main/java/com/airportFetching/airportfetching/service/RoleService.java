package com.airportFetching.airportfetching.service;

import com.airportFetching.airportfetching.model.Role;

public interface RoleService {
    Role findByName(String name);
}
