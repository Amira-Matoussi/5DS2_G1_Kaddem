package tn.esprit.spring.kaddem.entities;
import java.util.Date;
public class ContratDTO {
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

    // Getters and Setters
    public Integer getIdContrat() { return idContrat; }
    public void setIdContrat(Integer idContrat) { this.idContrat = idContrat; }

    public Date getDateDebutContrat() { return dateDebutContrat; }
    public void setDateDebutContrat(Date dateDebutContrat) { this.dateDebutContrat = dateDebutContrat; }

    public Date getDateFinContrat() { return dateFinContrat; }
    public void setDateFinContrat(Date dateFinContrat) { this.dateFinContrat = dateFinContrat; }

    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }

    public Boolean getArchive() { return archive; }
    public void setArchive(Boolean archive) { this.archive = archive; }

    public Integer getMontantContrat() { return montantContrat; }
    public void setMontantContrat(Integer montantContrat) { this.montantContrat = montantContrat; }
}
