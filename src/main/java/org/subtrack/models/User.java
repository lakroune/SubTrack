package org.subtrack.models;

public class User {
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String password;

    public User(String id, String nom, String prenom, String email, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }   
}
