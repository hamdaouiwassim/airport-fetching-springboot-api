package airportfetching.example.airportfetching.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Setter
@Getter
@Data
@Table(name="users")
@NoArgsConstructor
public class User implements UserDetails {

    @PrePersist
    protected void onCreate(){
        this.created_at = new Date(System.currentTimeMillis());

    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_at= new Date(System.currentTimeMillis());

    }

    @Id
    private String id;
    private String email;
    private String password;
    private String user_name;
    private String mobile_number;
    private String first_name;
    private String last_name;
    private Date created_at;
    private Date updated_at;

    @ManyToMany
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id") , inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.stream().forEach(i->authorities.add(new SimpleGrantedAuthority(i.getName())));
        return List.of(new SimpleGrantedAuthority(authorities.toString()));
    }


    public User(String mobile_number, String first_name , String last_name, String user_name, String email , String password,Set<Role> roles){
        this.id=email;
        this.email = email;
        this.mobile_number = mobile_number;
        this.user_name = user_name;
        this.roles = roles;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
