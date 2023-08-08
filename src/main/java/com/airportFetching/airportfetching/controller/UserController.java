package com.airportFetching.airportfetching.controller;

import com.airportFetching.airportfetching.auth.AuthenticationResponse;
import com.airportFetching.airportfetching.config.TokenProvider;
import com.airportFetching.airportfetching.dao.ScreenDAO;
import com.airportFetching.airportfetching.dao.UserDao;
import com.airportFetching.airportfetching.model.AuthToken;
import com.airportFetching.airportfetching.model.LoginUser;
import com.airportFetching.airportfetching.model.User;
import com.airportFetching.airportfetching.model.UserDto;
import com.airportFetching.airportfetching.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userRepository;



    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser)  {

        try{
            User user = userRepository.findByEmail(loginUser.getUsername()).orElseThrow(()->new NoSuchElementException("User not found"));
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getUsername(),
                            loginUser.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtil.generateToken(authentication);
            return ResponseEntity.ok(AuthenticationResponse.builder().roles(user.getRoles()).access_token(token).email(user.getEmail()).user_name(user.getUsername()).build());


        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
       }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDto user){

        try {
            if (userRepository.findByEmail(user.getEmail()).isPresent() ){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already existe with this email");
            }
            return ResponseEntity.ok(userService.save(user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something want wrong");

        }



    }



    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/adminping", method = RequestMethod.GET)
    public String adminPing(){
        return "Only Admins Can Read This";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/userping", method = RequestMethod.GET)
    public String userPing(){
        return "Any User Can Read This";
    }

}
