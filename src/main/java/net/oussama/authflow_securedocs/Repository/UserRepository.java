package net.oussama.authflow_securedocs.Repository;

import net.oussama.authflow_securedocs.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Ce stereotype Spring indique que cette classe appartient à la couche DAO (Data Access Layer)
// et permet d'interagir avec la base de données de manière abstraite.
public interface UserRepository extends JpaRepository<User, Integer> {
}
