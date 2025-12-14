package br.ifsp.scrumou.identity.model;

import jakarta.persistence.*;

@Entity
@Table(name = "identity_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;
    public String email;
    public String token;
    public UserType userType;

    public enum UserType {
        DEVELOPER,
        PM
    }
}