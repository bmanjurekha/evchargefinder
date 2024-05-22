package org.example.evchargefinder.repository;
import org.example.evchargefinder.model.AuthDetails;
import org.example.evchargefinder.model.UserFavDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFavRepository extends JpaRepository<UserFavDetails, Long>{
    List<UserFavDetails> findUserFavDetailsByAuthDetails(AuthDetails authDetails);
    UserFavDetails findUserFavDetailsByChargingStationID(String chargingStationID);

}
