package tn.esprit.spring.kaddem.entities;

import lombok.Getter;

import java.util.Date;

@Getter
public class ContratDTO {
    // Getters and Setters
    private Integer idContrat;
    private Date dateDebutContrat;
    private Date dateFinContrat;
    private String specialite;  // Representing enum as a String for easier JSON handling
    private Boolean archive;
    private Integer montantContrat;

    // Constructors
    public ContratDTO() {}

    public ContratDTO(Integer idContrat, Date dateDebutContrat, Date dateFinContrat, String specialite, Boolean archive, Integer montantContrat) {
        this.idContrat = idContrat;
        this.dateDebutContrat = dateDebutContrat;
        this.dateFinContrat = dateFinContrat;
        this.specialite = specialite;
        this.archive = archive;
        this.montantContrat = montantContrat;
    }

    public void setIdContrat(Integer idContrat) { this.idContrat = idContrat; }

    public void setDateDebutContrat(Date dateDebutContrat) { this.dateDebutContrat = dateDebutContrat; }

    public void setDateFinContrat(Date dateFinContrat) { this.dateFinContrat = dateFinContrat; }

    public void setSpecialite(String specialite) { this.specialite = specialite; }

    public void setArchive(Boolean archive) { this.archive = archive; }

    public void setMontantContrat(Integer montantContrat) { this.montantContrat = montantContrat; }
}