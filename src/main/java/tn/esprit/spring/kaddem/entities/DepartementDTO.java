package tn.esprit.spring.kaddem.entities;

import java.io.Serializable;

public class DepartementDTO implements Serializable {
    private Integer idDepart; // Include ID for updates
    private String nomDepart; // The name of the department

    // Default constructor
    public DepartementDTO() {}

    // Parameterized constructor
    public DepartementDTO(Integer idDepart, String nomDepart) {
        this.idDepart = idDepart;
        this.nomDepart = nomDepart;
    }

    // Getters and Setters
    public Integer getIdDepart() {
        return idDepart;
    }

    public void setIdDepart(Integer idDepart) {
        this.idDepart = idDepart;
    }

    public String getNomDepart() {
        return nomDepart;
    }

    public void setNomDepart(String nomDepart) {
        this.nomDepart = nomDepart;
    }
}