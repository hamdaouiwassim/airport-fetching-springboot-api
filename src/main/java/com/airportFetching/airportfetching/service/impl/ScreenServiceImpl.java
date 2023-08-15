package com.airportFetching.airportfetching.service.impl;

import com.airportFetching.airportfetching.dao.ScreenDAO;
import com.airportFetching.airportfetching.model.Screen;
import com.airportFetching.airportfetching.screens.ScreenRequest;
import com.airportFetching.airportfetching.screens.ScreenResponse;
import com.airportFetching.airportfetching.service.ScreenService;
import com.airportFetching.airportfetching.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "screenService")
public class ScreenServiceImpl implements ScreenService {

    @Autowired
    private ScreenDAO screenRepository;
//    public ResponseEntity<?> save(ScreenRequest screenRequest){
//        try{
//
//
//            Screen screen = screenRepository.save(new Screen(screenRequest.getCountry(),screenRequest.getType(),screenRequest.getName(),screenRequest.getCase_id() , screenRequest.getGender() , screenRequest.getBirth_date() ,screenRequest.getLocation(),screenRequest.getNationality()));
//            return ResponseEntity.ok(ScreenResponse.builder().body(screen).build());
//        }catch(Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }

    @Override
    public Screen save(ScreenRequest screenRequest) {
           //return null;
            Screen screen = screenRepository.save(new Screen(screenRequest.getCountry(),screenRequest.getType(),screenRequest.getName(),screenRequest.getCase_id() , screenRequest.getGender() , screenRequest.getBirth_date() ,screenRequest.getLocation(),screenRequest.getNationality()));
            return  screen;
            //return ResponseEntity.ok(ScreenResponse.builder().body(screen).build());

    }

    @Override
    public List<Screen> findAll() {
        List<Screen> screens = screenRepository.findAll();
        return  screens;
    }

    @Override
    public Screen findOne(String name) {
        return null;
    }
}
