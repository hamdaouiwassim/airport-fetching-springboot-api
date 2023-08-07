package airportfetching.example.airportfetching.repository;


import airportfetching.example.airportfetching.model.Role;
import airportfetching.example.airportfetching.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleCustomRepo {
    @PersistenceContext
    private EntityManager entityManager;
    public List<Role> getRole(User user){
        StringBuilder sql = new StringBuilder()
                .append("SELECT r.name as name\n"+
                                "FROM users u\n"+
                                "JOIN user_role ur ON u.id=ur.user_id\n"+
                                "JOIN roles r ON r.id = ur.role_id\n"+
                                "WHERE 1=1"
                        );

        if(user.getEmail()!=null){
            sql.append(" and email=:email");
        }
        NativeQuery<Role> query = ((Session)entityManager.getDelegate()).createNativeQuery(sql.toString());
        query.setParameter("email",user.getEmail());
        query.addScalar("name", StandardBasicTypes.STRING);
        query.setResultTransformer(Transformers.aliasToBean(Role.class));
        return query.list();
    }
}
