package org.example.evchargefinder.controller;
import org.example.evchargefinder.model.AuthDetails;
import org.example.evchargefinder.services.AuthService;
import org.example.evchargefinder.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/*")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtService jwtService;
    @GetMapping("/getToken/{username}/{password}")
    public ResponseEntity<String> getToken(@PathVariable String username,@PathVariable String password)
    {
        if(authService.authenticate(username,password)) {
            AuthDetails authDetails = new AuthDetails();
            authDetails.setUsername(username);
            authDetails.setPassword(password);
            //return  ResponseEntity.ok(authService.authenticate(username,password));
            return ResponseEntity.ok(jwtService.getToken(authDetails));
        }
        else
            return ResponseEntity.ok("Invalid Username or Password");
    }
    @PostMapping("/register-user/{username}/{password}")
    public ResponseEntity<String> registerUser(@PathVariable String username,@PathVariable String password) {
        AuthDetails authDetails = new AuthDetails();
        authDetails.setUsername(username);
        authDetails.setPassword(password);
        authDetails.setUsertoken(jwtService.generateToken(username,password));
        authService.save(authDetails);
        return ResponseEntity.ok("User Registered");
    }
}
