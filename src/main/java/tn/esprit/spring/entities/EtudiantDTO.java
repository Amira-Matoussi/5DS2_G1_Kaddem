package tn.esprit.spring.entities;


import java.io.Serializable;

public class EtudiantDTO implements Serializable {
    private String nom; // Replace with actual fields you need
    private String prenom; // Add more fields as necessary

    // Default constructor
    public EtudiantDTO() {}

    // Parameterized constructor
    public EtudiantDTO(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    // Getters and Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    // You can add more fields as necessary
}
