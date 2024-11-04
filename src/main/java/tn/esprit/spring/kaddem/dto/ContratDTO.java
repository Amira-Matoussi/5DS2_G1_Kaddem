package tn.esprit.spring.kaddem.dto;
import lombok.Data;
import tn.esprit.spring.kaddem.entities.Specialite;
import java.util.Date;
@Data
public class ContratDTO {
    private Integer idContrat;
    private Date dateDebutContrat;
    private Date dateFinContrat;
    private Specialite specialite;
    private Boolean archive;
    private Integer montantContrat;
    private Integer etudiantId; // Only the ID of the associated Etudiant, if needed

    public ContratDTO() {}

    public ContratDTO(Integer idContrat, Date dateDebutContrat, Date dateFinContrat, Specialite specialite,
                      Boolean archive, Integer montantContrat, Integer etudiantId) {
        this.idContrat = idContrat;
        this.dateDebutContrat = dateDebutContrat;
        this.dateFinContrat = dateFinContrat;
        this.specialite = specialite;
        this.archive = archive;
        this.montantContrat = montantContrat;
        this.etudiantId = etudiantId;
    }

}
