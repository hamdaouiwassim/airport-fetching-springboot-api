package com.airportFetching.airportfetching.auth;

import com.airportFetching.airportfetching.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String access_token;
    private String user_name;
    private Set<Role> roles;
    private String email;
}
