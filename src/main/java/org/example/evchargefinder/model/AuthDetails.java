package org.example.evchargefinder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
public class AuthDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;
    private String usertoken;
    @OneToMany(mappedBy = "authDetails", cascade = CascadeType.ALL)
    private List<UserFavDetails> userFavDetails;
}
