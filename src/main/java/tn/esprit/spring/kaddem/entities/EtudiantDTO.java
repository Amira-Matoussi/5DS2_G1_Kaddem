package tn.esprit.spring.kaddem.entities;

import java.io.Serializable;

public class EtudiantDTO implements Serializable {
    private Integer idEtudiant;
    private String nomE;
    private String prenomE;
    private String op; // We'll store the Option enum as a String in DTO

    // Default constructor
    public EtudiantDTO() {
    }

    // Constructor for creating a new Etudiant
    public EtudiantDTO(String nomE, String prenomE, String op) {
        this.nomE = nomE;
        this.prenomE = prenomE;
        this.op = op;
    }

    // Constructor with ID for updating
    public EtudiantDTO(Integer idEtudiant, String nomE, String prenomE, String op) {
        this.idEtudiant = idEtudiant;
        this.nomE = nomE;
        this.prenomE = prenomE;
        this.op = op;
    }

    // Getters and Setters
    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getNomE() {
        return nomE;
    }

    public void setNomE(String nomE) {
        this.nomE = nomE;
    }

    public String getPrenomE() {
        return prenomE;
    }

    public void setPrenomE(String prenomE) {
        this.prenomE = prenomE;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    @Override
    public String toString() {
        return "EtudiantDTO{" +
                "idEtudiant=" + idEtudiant +
                ", nomE='" + nomE + '\'' +
                ", prenomE='" + prenomE + '\'' +
                ", op='" + op + '\'' +
                '}';
    }
}