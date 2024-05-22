package org.example.evchargefinder.controller;
import org.example.evchargefinder.model.*;
import org.example.evchargefinder.services.AuthService;
import org.example.evchargefinder.services.JwtService;
import org.example.evchargefinder.services.UserFavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userfav/*")
public class UserFavController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserFavService userFavService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody FavPayload favPl)
    {
        AuthDetails authDetails = authService.authenticate(favPl.userToken);
        if(authDetails!=null &&  !favPl.chargingStationID.isEmpty()) {
            UserFavDetails userFavDetails = new UserFavDetails();
            userFavDetails.setAuthDetails(authDetails);
            userFavDetails.setChargingStationID(favPl.chargingStationID);
            userFavService.save(userFavDetails);
            return ResponseEntity.ok("User Favourite Charging Station saved successfully");
        }
        else
            return ResponseEntity.ok("Please register and login to add favourite stations");
    }
    @GetMapping("/UserFavStations")
    public ResponseEntity<List<UserFavDetails>>getUserFavDetails(@RequestBody FavPayload favPl) {
        if (favPl != null) {
            AuthDetails authDetails = authService.authenticate(favPl.userToken);
            if (authDetails != null) {
                List<UserFavDetails> userFavDetails = userFavService.getUserFavStation(authDetails);
                return ResponseEntity.status(201).body(userFavDetails);
            } else {
                return ResponseEntity.status(201).body(new ArrayList<>(null));
            }
        } else {
            return ResponseEntity.status(201).body(new ArrayList<>(null));
        }
    }
}
