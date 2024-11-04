package tn.esprit.spring.kaddem.dto;

import java.io.Serializable;

public class DepartmentDTO implements Serializable {
    private Integer idDepart; // Include ID for updates
    private String nomDepart; // The name of the department

    // Default constructor
    public DepartmentDTO() {}

    // Parameterized constructor
    public DepartmentDTO(Integer idDepart, String nomDepart) {
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
