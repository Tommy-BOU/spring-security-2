package fr.diginamic.spring_security_2.repositories;

import fr.diginamic.spring_security_2.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Integer> {
    public UserApp findByUsername(String username);
}
