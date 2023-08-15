package com.airportFetching.airportfetching.controller;



import com.airportFetching.airportfetching.model.Screen;
import com.airportFetching.airportfetching.screens.ScreenRequest;
import com.airportFetching.airportfetching.screens.ScreenResponse;
import com.airportFetching.airportfetching.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/screen")
public class ScreenController {

    @Autowired
    private ScreenService screenService;

   @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addScreen(@RequestBody ScreenRequest screenRequest) {



       try{

           Screen screen = screenService.save(screenRequest);
           // Screen screen = screenRepository.save(new Screen(screenRequest.getCountry(),screenRequest.getType(),screenRequest.getName(),screenRequest.getCase_id() , screenRequest.getGender() , screenRequest.getBirth_date() ,screenRequest.getLocation(),screenRequest.getNationality()));
            return ResponseEntity.ok(ScreenResponse.builder().body(screen).build());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllScreens() {

        try{

            List<Screen> screens = screenService.findAll();
            // Screen screen = screenRepository.save(new Screen(screenRequest.getCountry(),screenRequest.getType(),screenRequest.getName(),screenRequest.getCase_id() , screenRequest.getGender() , screenRequest.getBirth_date() ,screenRequest.getLocation(),screenRequest.getNationality()));

            return ResponseEntity.ok(ScreenResponse.builder().body(screens).build());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

}
