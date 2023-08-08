package com.airportFetching.airportfetching.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    
    private String username;
    private String password;
    private String email;
    private String businessTitle;
    private String company;



    public User getUserFromDto(){
        User user = new User();
        user.setUsername(email);
        user.setPassword(password);
        user.setEmail(email);
        user.setCompany(company);
        return user;
    }
    
}