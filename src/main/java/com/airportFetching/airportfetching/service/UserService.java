package com.airportFetching.airportfetching.service;

import com.airportFetching.airportfetching.model.User;
import com.airportFetching.airportfetching.model.UserDto;

import java.util.List;

public interface UserService {
    User save(UserDto user);
    List<User> findAll();
    User findOne(String username);
}
