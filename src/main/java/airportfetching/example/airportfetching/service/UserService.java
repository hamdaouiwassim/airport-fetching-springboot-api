package airportfetching.example.airportfetching.service;

import airportfetching.example.airportfetching.model.Role;
import airportfetching.example.airportfetching.model.User;

public interface UserService {

    User saveUser(User user);
    Role saveRole(Role role);

    void addToUser(String username , String rolename );
}
