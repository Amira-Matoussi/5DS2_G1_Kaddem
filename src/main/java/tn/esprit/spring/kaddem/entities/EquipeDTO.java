package tn.esprit.spring.kaddem.entities;

import java.io.Serializable;
import java.util.Set;

public class EquipeDTO implements Serializable {
    private Integer idEquipe; // If you want to include ID for updates
    private String nomEquipe;
    private String niveau; // Use String or create a separate DTO for Niveau if needed
    private Set<Integer> etudiantIds; // List of student IDs (assuming you don't want to send full Etudiant objects)

    // Default constructor
    public EquipeDTO() {
    }

    // Constructor for creating a new Equipe
    public EquipeDTO(String nomEquipe, String niveau) {
        this.nomEquipe = nomEquipe;
        this.niveau = niveau;
    }

    // Constructor for updating an existing Equipe
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

    public Set<Integer> getEtudiantIds() {
        return etudiantIds;
    }

    public void setEtudiantIds(Set<Integer> etudiantIds) {
        this.etudiantIds = etudiantIds;
    }

    @Override
    public String toString() {
        return "EquipeDTO{" +
                "idEquipe=" + idEquipe +
                ", nomEquipe='" + nomEquipe + '\'' +
                ", niveau='" + niveau + '\'' +
                '}';
    }
}