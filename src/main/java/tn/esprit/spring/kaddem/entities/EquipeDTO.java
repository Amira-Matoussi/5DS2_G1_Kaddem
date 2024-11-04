package tn.esprit.spring.kaddem.entities;

import java.io.Serializable;

public class EquipeDTO implements Serializable {
    private Integer idEquipe;
    private String nomEquipe;
    private String niveau;  // Representing enum as a String for easier JSON handling

    // Default constructor
    public EquipeDTO() {}

    // Parameterized constructor
    public EquipeDTO(Integer idEquipe, String nomEquipe, String niveau) {
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
        this.niveau = niveau;
    }

    // Getters and Setters
    public Integer getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Integer idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
}