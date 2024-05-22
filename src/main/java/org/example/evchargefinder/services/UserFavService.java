package org.example.evchargefinder.services;
import org.example.evchargefinder.model.AuthDetails;
import org.example.evchargefinder.model.UserFavDetails;
import org.example.evchargefinder.repository.AuthRepository;
import org.example.evchargefinder.repository.UserFavRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFavService {

    @Autowired
    UserFavRepository userFavRepository;

    public List<UserFavDetails> getUserFavStation(AuthDetails authDetails){
        return userFavRepository.findUserFavDetailsByAuthDetails(authDetails);
    }

    public  UserFavDetails getUserFavStationId(String chargingStationID){
        return  userFavRepository.findUserFavDetailsByChargingStationID(chargingStationID);
    }
    public void save(UserFavDetails userFavDetails){ userFavRepository.save(userFavDetails);}
}
