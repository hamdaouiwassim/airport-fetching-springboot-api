package airportfetching.example.airportfetching.repository;

import airportfetching.example.airportfetching.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String role);
}
