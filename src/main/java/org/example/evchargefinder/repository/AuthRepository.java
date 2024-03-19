package org.example.evchargefinder.repository;
import org.example.evchargefinder.model.AuthDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AuthRepository extends JpaRepository<AuthDetails, Long> {
    AuthDetails findByUsername(String username);
    AuthDetails findByusertoken(String userToken);

}

