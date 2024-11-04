package tn.esprit.spring.entities;


import java.io.Serializable;

public class EtudiantDTO implements Serializable {
    private Integer idEtudiant; // Optional, can be excluded if you don't want to provide ID when creating
    private String nomE;
    private String prenomE;
    private String op; // Assuming Option is represented as a String in DTO

    // Default constructor
    public EtudiantDTO() {}

    // Parameterized constructor
    public EtudiantDTO(String nomE, String prenomE, String op) {
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
}
