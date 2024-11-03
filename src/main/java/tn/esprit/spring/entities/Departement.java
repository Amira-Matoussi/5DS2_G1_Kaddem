package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Departement implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDepart;

    @Column(name = "nom_depart")
    private String nomDepart;

    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Etudiant> etudiants;

    // Constructor with nomDepart parameter
    public Departement(String nomDepart) {
        this.nomDepart = nomDepart;
    }

    // Constructor with idDepart and nomDepart parameters
    public Departement(Integer idDepart, String nomDepart) {
        this.idDepart = idDepart;
        this.nomDepart = nomDepart;
    }

    // Override toString() method for better logging and debugging
    @Override
    public String toString() {
        return "Departement{" +
                "idDepart=" + idDepart +
                ", nomDepart='" + nomDepart + '\'' +
                '}';
    }
}