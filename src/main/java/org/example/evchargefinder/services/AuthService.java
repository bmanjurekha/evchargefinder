package org.example.evchargefinder.services;

import org.example.evchargefinder.model.AuthDetails;
import org.example.evchargefinder.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    AuthRepository authRepository;

    public boolean authenticate(String username, String password) {
        AuthDetails auth = authRepository.findByUsername(username);
        if(auth!=null)
            return auth.getPassword().equals(password);
        else
            return false;
    }

    public AuthDetails authenticate(String usertoken){
        return authRepository.findByusertoken(usertoken);
    }
    public void save(AuthDetails authDetails){ authRepository.save(authDetails);}
}
