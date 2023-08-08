package com.airportFetching.airportfetching.service;


import com.airportFetching.airportfetching.dao.ScreenDAO;
import com.airportFetching.airportfetching.model.Screen;
import com.airportFetching.airportfetching.model.User;
import com.airportFetching.airportfetching.model.UserDto;
import com.airportFetching.airportfetching.screens.ScreenRequest;
import com.airportFetching.airportfetching.screens.ScreenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScreenService {

    Screen save(ScreenRequest screen);
    List<Screen> findAll();
    Screen findOne(String name);


}
