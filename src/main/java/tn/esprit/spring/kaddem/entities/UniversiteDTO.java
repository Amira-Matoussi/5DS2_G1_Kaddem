package tn.esprit.spring.kaddem.entities;
import java.io.Serializable;
public class UniversiteDTO implements Serializable {
    private String nomUniv; // Match the entity field name

    // Default constructor
    public UniversiteDTO() {}

    // Parameterized constructor
    public UniversiteDTO(String nomUniv) {
        this.nomUniv = nomUniv;
    }

    // Getters and Setters
    public String getNomUniv() {
        return nomUniv;
    }

    public void setNomUniv(String nomUniv) {
        this.nomUniv = nomUniv;
    }
}
